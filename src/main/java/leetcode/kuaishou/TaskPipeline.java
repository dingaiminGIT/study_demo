package leetcode.kuaishou;

import java.util.*;

/**
 * 任务调度器
 *
 * 在复杂的系统中往往有复杂的任务调度pipeline，任务之间存在依赖关系。
 * 比如有5个任务A、B、C、D、E，其中任务的依赖关系为 :  A -> B; B -> C;  D -> C;  C -> E。
 * A可以先运行，A完成后才能运行B任务，B任务完成后才能运行C任务，
 * D任务没有依赖的前置任务，D运行之后才能运行C任务。
 * C任务有2个前置依赖任务 B 和 D，只有这两个任务都完成后才能运行。
 * C任务结束后才能运行E任务。
 * 如果任务依赖关系设计的不合理，出现循环依赖，那么这个pipeline就无法正常执行了
 *
 * 设计一个任务调度器，读取输入的任务依赖关系图，
 * 首先判定是否是一个合法可执行的任务pipeline，如果是非法的则输出错误信息。
 * 如果是可执行的pipeline则通过线程池来实现任务的调度（把任务放到线程池中去执行），
 * 注意需要保证任务的前后依赖关系。
 * 输入的任务依赖关系格式，
 * 首先是任务依赖关系个数N，然后是N行，每一行代表一个任务依赖关系（比如 A -> B)，每个任务用一个大写字母表示
 *
 * @author: dingaimin
 * @date: 2021/1/24 21:24
 */
public class TaskPipeline {

    /**
     * 任务调度的流程
     * 1.定义一个Job维护各个任务的依赖关系
     * 2.找到Job的根节点任务，根节点任务没有父任务，任务被调度时，直接执行
     * 3.执行完根节点后，检查该节点是否有子节点，没有则不继续，有的话，并行执行所有的子节点的任务
     * 4.子节点任务被调度后，检查父任务的执行状态，如果已经执行完成，自己就可以执行了，如果父任务还未执行完成需要等待
     * 5.最子任务执行完后，整个Job的调度结束
     *
     * A -> B
     * B -> C
     * D -> C
     * C -> E
     * E -> F
     *
     * 这个 Job 的调度任务的根节点任务是 F
     * F 的子任务是 E
     * E 的子任务是 C
     * C 有 B 和 D 两个子任务
     * B 的子任务是 A
     */


    /**
     * 保存去重后的任务
     */
    private static Set<String> tasks = new HashSet<>();
    private static Map<String, TaskConfig>  taskConfigMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入任务依赖关系 N，然后再输入任务依赖关系，一行代表一个任务依赖关系");
        while (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                String[] arr = line.split(" -> ");
                tasks.add(arr[0]);
                tasks.add(arr[1]);
                // 子任务
                TaskConfig taskConfig1 = taskConfigMap.get(arr[0]);
                if (taskConfig1 == null) {
                    TaskConfig task = new TaskConfig(arr[0], new ArrayList<TaskConfig>(), new ArrayList<TaskConfig>());
                    taskConfigMap.put(arr[0], task);
                }
                // 父任务
                TaskConfig taskConfig2 = taskConfigMap.get(arr[1]);
                if (taskConfig2 == null) {
                    TaskConfig task = new TaskConfig(arr[1], new ArrayList<TaskConfig>(), new ArrayList<TaskConfig>());
                    taskConfigMap.put(arr[1], task);
                }
                // 构造依赖关系
                taskConfig1 = taskConfigMap.get(arr[0]);
                taskConfig2 = taskConfigMap.get(arr[1]);
                List<TaskConfig> parents = taskConfig1.getParents();
                parents.add(taskConfig2);
                taskConfig1.setParents(parents);
                List<TaskConfig> children = taskConfig2.getChildren();
                children.add(taskConfig1);
                taskConfig2.setChildren(children);
            }
            System.out.println("size:" + tasks.size());
            break;
        }

        // 判断任务依赖的合法性
        boolean verify = verify(taskConfigMap);
        if (verify) {
            System.out.println("这是一个有 " + tasks.size() + " 个任务的pipeline，是合法的可执行的任务链");
        } else {
            System.out.println("这是一个有 " + tasks.size() + " 个任务的pipeline，任务之间存在循环依赖，是不可以正常执行的任务链");
        }
        // 任何依赖合法，开始按次序执行任务

    }

    /**
     * 判断任务依赖是否合法
     *
     * 判断任务依赖的合法性，任务的父任务递归最后依赖自己的，这种情况就是循环依赖，非法
     *
     * @param taskConfigMap
     * @return
     */
    private static boolean verify(Map<String, TaskConfig> taskConfigMap) {
        for (String key : taskConfigMap.keySet()) {
            TaskConfig taskConfig = taskConfigMap.get(key);
            List<TaskConfig> parents = taskConfig.getParents();
            boolean verify = verify(key, parents);
            if (verify) {
                continue;
            } else {
                return false;
            }
        }
        return true;

    }

    /**
     * 递归调用验证任务依赖的合法性
     *
     * @param name
     * @param parents
     * @return
     */
    private static boolean verify(String name, List<TaskConfig> parents) {
        if (parents == null || parents.size() == 0) {
            return true;
        }
        for (TaskConfig tc : parents) {
            if (name.equals(tc.getName())) {
                return false;
            }
        }
        for (TaskConfig tc : parents) {
            boolean verify = verify(name, tc.getParents());
            if (verify) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    static class JobConfig {
        private String name;
        /**
         * 子任务根节点
         */
        private TaskConfig task;
    }

    /**
     * 子任务之间的依赖关系
     */
   static class TaskConfig{
        /**
         * 任务名称
         */
        String name;
        /**
         * 父节点
         */
        List<TaskConfig> parents;
        /**
         * 子节点
         */
        List<TaskConfig> children;

        public TaskConfig(String name, List<TaskConfig> parents, List<TaskConfig> children) {
            this.name = name;
            this.parents = parents;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TaskConfig> getParents() {
            return parents;
        }

        public void setParents(List<TaskConfig> parents) {
            this.parents = parents;
        }

        public List<TaskConfig> getChildren() {
            return children;
        }

        public void setChildren(List<TaskConfig> children) {
            this.children = children;
        }
    }

    /**
     * 每次调度的Job
     */
    static class Job{
        /**
         * 所属的 jobconfig
         */
        JobConfig jobConfig;
        /**
         * 任务执行状态
         */
        int status;
    }

    /**
     * 每次任务调度的task
     */
    static class Task{
        /**
         * 所属的taskconfig
         */
        TaskConfig taskConfig;
        /**
         * 所属的job
         */
        Job job;
        /**
         * 执行状态
         */
        int status;
        /**
         * 父task
         */
        List<Task> parentTasks;

    }


}

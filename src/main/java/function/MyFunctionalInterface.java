package function;

/**
 * 函数式接口：有且仅有一个抽象方法，但可以有多个非抽象方法的接口
 *
 */
@FunctionalInterface
interface MyFunctionalInterface {
    void eat(String food);

    static void main(String[] args) {
        // 使用lambda表达式表示接口的实现
        MyFunctionalInterface people = (food -> System.out.println("吃：" + food));
        people.eat("鱼");
    }
}

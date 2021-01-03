package sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelTest {

    public static void main(String[] args) {
        // 配置规则
        initFlowRules();

        while (true) {
            try {
                Entry entry = SphU.entry("HelloWorld");
            } catch (BlockException e) {
                // 处理被流控的逻辑
                System.out.println("blocked");
            }
        }
    }

    /**
     * 定义规则
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setRefResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置限制的 QPS
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}

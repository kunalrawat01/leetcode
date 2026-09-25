import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(dfs(expression));
    }

    private Set<String> dfs(String exp) {
        Set<String> res = new TreeSet<>();
        int j = exp.indexOf('}');
        
        if (j == -1) { 
            res.add(exp);
            return res;
        }
        
        int i = exp.lastIndexOf('{', j);
        String prefix = exp.substring(0, i);
        String suffix = exp.substring(j + 1);
        
        String[] parts = exp.substring(i + 1, j).split(",");
        for (String part : parts) {
            res.addAll(dfs(prefix + part + suffix));
        }
        
        return res;
    }
}

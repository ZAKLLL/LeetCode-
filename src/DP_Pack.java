/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2020-02-05 00:00
 **/
public class DP_Pack {

    /**
     * @param cost 每个物品需要占据的容量
     * @param w    每个物品的价值
     * @param n    物品数量
     * @param V    背包容量
     * @return
     */
    public int zerOnePack(int[] cost, int[] w, int n, int V) {
        int[][] dp = new int[n + 1][V + 1];

        for (int i = 1; i <= n; i++) {
            //从cost[i]开始不用考虑小于问题
            for (int v = cost[i]; v < V; v++) {
                dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - cost[i]] + w[i]);
            }
        }
        return dp[n][V];
    }

    //背包中的物品只能选一次
    public int zerOnePack2(int[] cost, int[] w, int n, int V) {
        int[] dpv = new int[V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = V; v >= cost[i]; v--) {
                dpv[v] = Math.max(dpv[v], dpv[v - cost[i]] + w[i]);
            }
        }
        return dpv[V];
    }

    //背包中的物品能选任意多次
    public int complete(int[] cost, int[] w, int n, int V) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = cost[i]; v <= V; v++) {
                dp[v] = Math.max(dp[v], dp[v - cost[i]] + w[i]);
            }
        }
        return dp[V];
    }


}

package algorithm;

import java.util.HashMap;
import java.util.List;

/**
 * @program: suanfa
 * @description: 并查集
 * @author: ZakL
 * @create: 2020-01-02 18:18
 **/
public class UnionFindSet {
    public HashMap<UnioNode, UnioNode> fatherMap;
    public HashMap<UnioNode, Integer> sizeMap; //每个并查集的head节点和这个并查集的节点数量

    public UnionFindSet(List<UnioNode> nodes) {
        makeSet(nodes);
    }

    /**
     * 初始化并查集
     *
     * @param nodes
     */
    private void makeSet(List<UnioNode> nodes) {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (UnioNode node : nodes) {
            fatherMap.put(node, node);   //head节点的父节点是自己
            sizeMap.put(node, 1);       //head节点所在的并查集大小默认为1
        }
    }

    /**
     * @param node
     * @return 当前节点所在的父节点
     */
    public UnioNode findHead(UnioNode node) {
        UnioNode father = fatherMap.get(node);
        if (father != node) {
            father = findHead(father);
        }
        fatherMap.put(node, father); //使每个节点的父节点都是head节点
        return father;
    }

    /**
     * @param a
     * @param b
     * @return 两个节点是否在同一并查集
     */
    public boolean isSameSet(UnioNode a, UnioNode b) {
        return findHead(a) == findHead(b);
    }

    private void union(UnioNode a, UnioNode b) {
        if (a == null || b == null) return;
        UnioNode aHead = findHead(a);
        UnioNode bHead = findHead(b);
        if (aHead != bHead) {
            int sizeA = sizeMap.get(aHead);
            int sizeB = sizeMap.get(bHead);
            if (sizeA > sizeB) {
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, sizeA + sizeB);
            } else {
                fatherMap.put(aHead, bHead);
                sizeMap.put(bHead, sizeA + sizeB);
            }
        }
    }
}


class UnioNode {

}

/*------------------------并查集使用数组表示----------------------------------*/

/**
 * 不带路径压缩的并查集
 */
class UnionFindSetWithArr {
    private int[] fm;

    private void makeSet(int n) {
        fm = new int[n];
        for (int i = 0; i < n; i++) {
            fm[i] = i;
        }
    }

    private int find(int node) {
        int fa = fm[node];
        if (fa != node) {
            fa = find(fa);
        }
        fm[node] = fa;
        return fa;
    }

    private boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private void union(int a, int b) {
        fm[find(b)] = find(a);
    }
}
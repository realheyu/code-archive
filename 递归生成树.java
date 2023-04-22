package com.chico.client.util.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author heyu
 * @date 2023/4/21
 */
public class TreeUtil {


    /**
     * 构建树形结构
     *
     * @param nodes    待构建的节点列表
     * @param <T>      节点类型
     * @param parentId 从哪个节点开始生成树
     * @return 根节点列表
     */
    public static <T extends TreeNode<T>> List<T> buildTree(List<T> nodes, Long parentId) {
        // 将节点列表按照父ID分组
        Map<Long, List<T>> nodeGroup = nodes.stream()
                .filter(node -> node.getParentId() != null)
                .collect(Collectors.groupingBy(TreeNode::getParentId));
        // 从根节点开始构建树
        return buildTree(nodeGroup, parentId);
    }

    /**
     * 递归构建树形结构
     *
     * @param nodeGroup 节点列表按照父ID分组后的Map
     * @param parentId  父节点ID
     * @param <T>       节点类型
     * @return 子节点列表
     */
    private static <T extends TreeNode<T>> List<T> buildTree(Map<Long, List<T>> nodeGroup, Long parentId) {
        // 存放子节点的列表
        List<T> tree = new ArrayList<>();
        // 获取当前节点的子节点列表
        List<T> children = nodeGroup.get(parentId);
        if (children != null) {
            // 遍历子节点列表
            for (T child : children) {
                // 递归构建子节点的子树
                List<T> subTree = buildTree(nodeGroup, child.getId());
                // 将子树设置为当前节点的子节点
                child.setChildren(subTree);
                // 将当前节点添加到子节点列表中
                tree.add(child);
            }
        }
        // 返回子节点列表
        return tree;
    }


}

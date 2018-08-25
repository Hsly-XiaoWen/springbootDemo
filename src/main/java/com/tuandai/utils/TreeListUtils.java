package com.tuandai.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 肖文 on 2018/8/25
 */
public class TreeListUtils {

    private static List<Department> newList;

    public TreeListUtils() {
        newList = new ArrayList<>();
    }

    /**
     * 向新list中装入根节点并递归子节点
     */
    public List<Department> setRootNode(List<Department> treeList){
        List<Department> rootNode = getRootNode(treeList);
        newList.addAll(rootNode);
        rootNode.forEach(x -> sortNode(x, treeList));
        return newList;
    }

    /**
     * 获得根节点
     */
    private List<Department> getRootNode(List<Department> treeList) {
        List<Department> rootList = new ArrayList<>();
        treeList.forEach(x ->{
            if (x.getParentId() == 0) {
                rootList.add(x);
            }
        });
        return rootList;
    }

    /**
     * 递归子节点
     * @param department  各个根节点
     * @param treeList  存放所有节点的list
     * @return childList
     */
    private void sortNode(Department department, List<Department> treeList) {
        Set<Department> childList = getChild(department,treeList);
        if (childList != null) {
            department.setChildren(childList);
            childList.forEach(x -> sortNode(x, treeList));
        }
    }

    /**
     * 获得根节点的子节点
     * @param department 各个根节点
     * @param treeList 存放所有节点的list
     * @return childList
     */
    private Set<Department> getChild(Department department, List<Department> treeList) {
        Set<Department> childList = new HashSet<>();
        treeList.forEach(x ->{
            if (x.getParentId().equals(department.getDeptId())) {
                childList.add(x);
            }
        });
        return childList;
    }
}

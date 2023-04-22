/**
     * 根据传入的菜单生成树
     *
     * @return
     */
    public List<ZkMenu> treeData(List<ZkMenu> list) {
        // 通过循环的方式获得菜单。这里将 0 传入，因为0在此次数据表中代表一级菜单
        List<ZkMenu> dataLoop = getTreeDataLoop(0L, list);
        return dataLoop;
    }




  

    /**
     * 生成tree
     *
     * @param id
     * @return
     */
    private List<ZkMenu> getTreeDataLoop(Long id, List<ZkMenu> menus) {
        // 新建List来存放一级菜单
        List<ZkMenu> tree = new ArrayList<>();
        Map<Long, ZkMenu> map = new HashMap<>(256);
        // 将所有数据, 以键值对的形式装入map中
        for (ZkMenu menu : menus) {
            map.put(menu.getId(), menu);
        }
        for (ZkMenu menu : menus) {
            // 如果id是父级的话就放入tree中
            if (menu.getParentId().equals(id)) {
                tree.add(menu);
            } else {
                // 子级通过父id获取到父级的类型
                ZkMenu parent = map.get(menu.getParentId());
                // 父级获得子级，再将子级放到对应的父级中
                if (parent == null) {
                    continue;
                }
                parent.getChildren().add(menu);
            }
        }
        return tree;
    }

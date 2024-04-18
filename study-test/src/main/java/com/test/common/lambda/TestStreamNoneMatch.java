package com.test.common.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-25 13:36:39
 **/
public class TestStreamNoneMatch {
    public static void main(String[] args) {
        List<Entity> entityList = List.of(new Entity(1), new Entity(2), new Entity(3));
        List<VO> voList = List.of(new VO(2), new VO(3));
        
        // 剔除与值对象列表中相同 ID 的对象
        List<Entity> filteredEntityList = entityList.stream()
                .filter(entity -> voList.stream().noneMatch(vo -> vo.getId() == entity.getId()))
                .collect(Collectors.toList());
        
        // 输出结果
        System.out.println("Original Entity List: " + entityList.toString());
        System.out.println("Value Object List: " + voList.toString());
        System.out.println("Filtered Entity List: " + filteredEntityList.toString());
    }
}

class Entity {
    private int id;
    
    public Entity(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}

class VO {
    private int id;
    
    public VO(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "VO{" +
                "id=" + id +
                '}';
    }
}

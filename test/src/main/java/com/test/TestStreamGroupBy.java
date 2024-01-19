package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-18 10:08:09
 **/
public class TestStreamGroupBy {
    public static void main(String[] args) {
        List<LeaderBoard> leaderBoardList = Arrays.asList(
                new LeaderBoard("张三", 10, 1, 1, 3),
                new LeaderBoard("李四", 20, 2, 1, 2),
                new LeaderBoard("王五", 30, 3, 1, 1),
                new LeaderBoard("王二", 40, 4, 2, 1),
                new LeaderBoard("龙六", 50, 5, 2, 2));
        Map<Integer, List<LeaderBoard>> leaderBoardMap = leaderBoardList.stream().collect(Collectors.groupingBy(LeaderBoard::getBoardId));
        leaderBoardMap.forEach((boardId, boardList) -> boardList.sort(Comparator.comparingInt(LeaderBoard::getRank)));
        
        leaderBoardMap.forEach((boardId, boardList) -> {
            System.out.println("boardId:" + boardId);
            boardList.forEach(leaderBoard -> {
                System.out.println("name:" + leaderBoard.getName() + " age:" + leaderBoard.getAge() + " id:" + leaderBoard.getId() + " rank:" + leaderBoard.getRank());
            });
        });
        
        long board1 = leaderBoardList.stream().filter(item -> StringUtils.equalsAny(String.valueOf(item.getBoardId()), "1", "2")).count();
        System.out.println("board1:" + board1);
        
        String num = "3";
        System.out.println(StringUtils.equalsAny(num, "1", "2"));
        
    }
}

@Data
@AllArgsConstructor
class LeaderBoard {
    private String name;
    private int age;
    private Integer id;
    private Integer boardId;
    private Integer rank;
    
    
}

package com.project.ownote.board;

import com.project.ownote.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDaoT {
    public List<Board> selectAll();
    public Board view(int noticeNum);
    public void write(String noticeTitle, String noticeWriter, String noticeDivision, String noticeContent);
    public void delete(int noticeNum);

}

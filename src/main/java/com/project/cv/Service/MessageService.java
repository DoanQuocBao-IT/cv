package com.project.cv.Service;

import com.project.cv.Dto.MessageDto;
import com.project.cv.Model.Messages;

import java.util.List;

public interface MessageService {
    List<MessageDto> findBySenderAndReceiverOrderByCreateAtAsc(int receiver_id);
    List<MessageDto> findBySenderOrReceiverOrderByCreateAtAsc();
    void sendMessage(Messages messages, int receiver_id);
}

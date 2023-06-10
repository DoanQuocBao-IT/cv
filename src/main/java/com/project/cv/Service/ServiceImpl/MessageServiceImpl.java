package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.MessageDto;
import com.project.cv.Model.Messages;
import com.project.cv.Model.User;
import com.project.cv.Repository.MessageRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<MessageDto> findBySenderAndReceiverOrderByCreateAtAsc(int receiver_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findUserByName(authentication.getName());
        User receiver=userRepository.findById(receiver_id).get();
        List<Messages> messages=messageRepository.findBySenderAndReceiverOrSenderAndReceiverOrderByCreateAtDesc(sender,receiver,receiver,sender);
        ModelMapper modelMapper=new ModelMapper();
        return messages.stream().map(message -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> findBySenderOrReceiverOrderByCreateAtAsc() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findUserByName(authentication.getName());
        List<Messages> messages=messageRepository.findBySenderOrReceiverOrderByCreateAtAsc(sender);
        ModelMapper modelMapper=new ModelMapper();
        return messages.stream().map(message -> modelMapper.map(message, MessageDto.class)).collect(Collectors.toList());
    }

    @Override
    public void sendMessage(Messages messages,int receiver_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findUserByName(authentication.getName());
        User receiver=userRepository.findById(receiver_id).get();
        messages.setSender(sender);
        messages.setReceiver(receiver);
        messages.setCreateAt(new Date());
        messageRepository.save(messages);
    }
}

package com.hotel.service.impl;

import com.hotel.entity.Liuyan;
import com.hotel.mapper.LiuyanMapper;
import com.hotel.service.LiuyanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LiuyanServiceImpl implements LiuyanService {

    private final LiuyanMapper liuyanMapper;

    @Override
    public boolean addLiuyan(Liuyan liuyan) {
        return liuyanMapper.insert(liuyan) > 0;
    }

    @Override
    public boolean deleteLiuyan(Long serNum) {
        return liuyanMapper.deleteByPrimaryKey(serNum) > 0;
    }

    @Override
    public Liuyan getLiuyanById(Long serNum) {
        return liuyanMapper.selectByPrimaryKey(serNum);
    }

    @Override
    public List<Liuyan> getAllLiuyans() {
        return liuyanMapper.selectAll();
    }
}

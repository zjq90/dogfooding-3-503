package com.hotel.service;

import com.hotel.entity.Liuyan;
import java.util.List;

public interface LiuyanService {

    boolean addLiuyan(Liuyan liuyan);

    boolean deleteLiuyan(Long serNum);

    Liuyan getLiuyanById(Long serNum);

    List<Liuyan> getAllLiuyans();
}

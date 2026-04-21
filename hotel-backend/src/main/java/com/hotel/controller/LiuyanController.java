package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Liuyan;
import com.hotel.service.LiuyanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liuyan")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiuyanController {

    private final LiuyanService liuyanService;

    @GetMapping("/list")
    public Result<List<Liuyan>> getLiuyanList() {
        List<Liuyan> list = liuyanService.getAllLiuyans();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Liuyan> getLiuyanById(@PathVariable Long id) {
        Liuyan liuyan = liuyanService.getLiuyanById(id);
        return Result.success(liuyan);
    }

    @PostMapping("/add")
    public Result<String> addLiuyan(@RequestBody Liuyan liuyan) {
        boolean success = liuyanService.addLiuyan(liuyan);
        return success ? Result.success("留言成功") : Result.error("留言失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteLiuyan(@PathVariable Long id) {
        boolean success = liuyanService.deleteLiuyan(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}

package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Reserve;
import com.hotel.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReserveController {

    private final ReserveService reserveService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getReserveList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Reserve> list = reserveService.getReservesByPage(pageNum, pageSize);
        int total = reserveService.getReserveCount();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", total);
        return Result.success(data);
    }

    @GetMapping("/user/{userName}")
    public Result<List<Reserve>> getReservesByUserName(@PathVariable String userName) {
        List<Reserve> list = reserveService.getReservesByUserName(userName);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Reserve> getReserveById(@PathVariable String id) {
        Reserve reserve = reserveService.getReserveById(id);
        return Result.success(reserve);
    }

    @PostMapping("/add")
    public Result<String> addReserve(@RequestBody Reserve reserve) {
        boolean success = reserveService.addReserve(reserve);
        return success ? Result.success("预订成功") : Result.error("预订失败");
    }

    @PutMapping("/update")
    public Result<String> updateReserve(@RequestBody Reserve reserve) {
        boolean success = reserveService.updateReserve(reserve);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteReserve(@PathVariable String id) {
        boolean success = reserveService.deleteReserve(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}

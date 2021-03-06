package com.eduonline.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.common.R;
import com.eduonline.eduservice.entity.EduTeacher;
import com.eduonline.eduservice.entity.query.QueryTeacher;
import com.eduonline.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/eduservice/eduteacher")
public class EduTeacherController {
    @Autowired
    EduTeacherService teacherService;

    //模拟返回用户信息
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").
                data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //模拟登录
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //添加讲师
    @PostMapping("addEduTeacher")
    public R addEduTeacher(@RequestBody EduTeacher teacher){
        boolean result = teacherService.save(teacher);
        if(result) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    //按照条件分页查询 RequestBody 必须使用post
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    public R getMoreCondtionPageList(@PathVariable long page,
                                     @PathVariable long limit,
                                     @RequestBody(required = false) QueryTeacher query){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        IPage<EduTeacher> list = teacherService.getCondtionPageList(teacherPage,query);
        long total = list.getTotal(); //总条数
        List<EduTeacher> records = list.getRecords(); //当前页的具体数据
        return R.ok().data("total",total).data("items",records);
    }

    //分页查询所有讲师
    @GetMapping("pageList/{page}/{limit}")
    public R getTeacherPageList(@PathVariable long page,
                                @PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        IPage<EduTeacher> list = teacherService.page(teacherPage, null);
        long total = list.getTotal(); //总条数
        List<EduTeacher> records = list.getRecords(); //当前页的具体数据
        return R.ok().data("total",total).data("items",records);
    }

    //查询所有讲师
    @GetMapping("getAllTeacherList")
    public R getAllTeacherList(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //按照id查询讲师
    @GetMapping("getTeacherInfoById/{id}")
    public R getTeacherInfoById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        if (teacher!=null){
            return R.ok().data("items",teacher);
        }
        return R.error();
    }

    //按照修改讲师
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean result = teacherService.updateById(eduTeacher);
        if (result){
           return R.ok();
        }
        return R.error();
    }

    //通过id逻辑删除讲师
    @DeleteMapping("{id}")
    public R deleteTeacherById(@PathVariable String id){
         teacherService.removeById(id);

        return R.ok();
    }
}


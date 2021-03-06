package com.eduonline.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eduonline.eduservice.entity.dto.form.EduAllCourseInfoDto;
import com.eduonline.eduservice.entity.dto.form.EduCourseInfoDto;
import com.eduonline.eduservice.entity.query.QueryCourse;
import com.eduonline.eduservice.vo.FrontCourseInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程信息
    String insertCourseInfo(EduCourseInfoDto eduCourseInfoDto);

    //根据id查询课程信息
    EduCourseInfoDto getCourseInfoById(String id);

    //更新课程信息
    String updateCourseInfo(EduCourseInfoDto eduCourseInfoDto);

    //按条件分页查询课程信息
    IPage<EduCourse> getCondtionPageList(Page<EduCourse> coursePage, QueryCourse query);

    //根据课程id删除
    boolean deleteCourseById(String courseId);

    //根据课程id查询课程详细信息
    EduAllCourseInfoDto getAllCourseInfoById(String id);

    //根据课程id修改课程发布状态
    boolean updateCourseStatus(String id);

    //前台 根据教师id查询
    List<EduCourse> getCourseInfoByTeacherId(String teacherId);

    //前台 分页获取所有课程
    Map<String, Object> getFrontAllTeacherList(Page<EduCourse> eduCoursePage);

    //前台 根据课程id查询课程基本信息 课程描述信息 讲师信息 分类信息
    FrontCourseInfoVo getAllCourseInfoByCourseId(String courseId);
}

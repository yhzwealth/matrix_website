package com.chuang.bootplus.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.base.utils.StringUtil;
import com.chuang.bootplus.entity.Blog;
import com.chuang.bootplus.entity.Comments;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.BlogMapper;
import com.chuang.bootplus.po.blog.BlogOfProblemPO;
import com.chuang.bootplus.po.project.UserMap;
import com.chuang.bootplus.po.blog.BlogRankPagePO;
import com.chuang.bootplus.po.blog.BlogScoreAddPO;
import com.chuang.bootplus.po.blog.BlogSubmitPO;
import com.chuang.bootplus.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.ProblemService;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.blog.BlogVO;
import com.chuang.bootplus.vo.blog.RankVO;
import com.chuang.bootplus.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang
 * @since 2022-03-09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final UserService userService;
    private final ProblemService problemService;

    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired

    @Override
    public ApiResponse<Void> addScore(BlogScoreAddPO po) {
        Blog blog = getOne(new LambdaQueryWrapper<Blog>().eq(Blog::getId, po.getBlogId()));
        Blog blog1 = blog.setScore(po.getScore());
        boolean b = updateById(blog1);
        if (!b) {
            throw new BusException("打分失败");
        }

        User byId = userService.getById(blog.getUserId());

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("提醒博客已被打分邮件");//主题
            mailMessage.setText("matrix工作室提醒您，您的博客 " + blog.getLink() + " 被打分为" + po.getScore() + "分，且已经进行了评价，快去你的博客里面看看吧~");//内容
            String str = byId.getEmail();
            System.out.println(str);
            mailMessage.setTo(str);//发给谁
            // mailMessage.setFrom("1029366100@qq.com");//你自己的邮箱
            mailMessage.setFrom("3033616351@qq.com");//你自己的邮箱
            mailSender.send(mailMessage);//发送
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ApiResponse<Void>().setReMsg("打分成功");
    }

    @Override
    public ApiResponse<RankVO> ranking() {
        List<User> list = userService.list(new LambdaQueryWrapper<User>());
        List<Integer> collect = list.stream().map(user ->
                count(new LambdaQueryWrapper<Blog>().eq(Blog::getUserId, user.getId()))).collect(Collectors.toList());

        UserMap[] users = new UserMap[list.size()];
        for(int i=0;i<list.size();i++){
            UserMap userMap = new UserMap();
            userMap.setUser(BeanUtil.beanA2beanB(list.get(i), UserVO.class));
            userMap.setCount(collect.get(i));
            users[i] = userMap;
        }


        Arrays.sort(users);
        RankVO rankVO = new RankVO();
        rankVO.setUsers(users);
        return new ApiResponse<>(rankVO);
    }

    @Override
    public ApiResponse<Void> blogSubmit(BlogSubmitPO po) {
        if(userService.getById(po.getUserId())==null){
            throw new BusException("用户不存在");
        }
        if (problemService.getById(po.getProId())==null){
            throw new BusException("题目不存在");
        }
        if (getOne(new LambdaQueryWrapper<Blog>().eq(Blog::getUserId,po.getUserId()).eq(Blog::getProId,po.getProId()))==null){
            Blog blog= BeanUtil.beanA2beanB(po,Blog.class);
            blog.setUserName(userService.getById(po.getUserId()).getUsername());
            save(blog);
        }else {
            Blog one = getOne(new LambdaQueryWrapper<Blog>().eq(Blog::getUserId, po.getUserId()).eq(Blog::getProId, po.getProId())
                    .eq(Blog::getProName,po.getProName()));
//            Blog blog = BeanUtil.beanA2beanB(po, Blog.class);
            Blog blog = BeanUtil.beanA2beanB(one, Blog.class);
            blog.setLink(po.getLink());
//            blog.setId(one.getId());
//            blog.setUserName(one.getUserName());
//            removeById(one.getId());
//            save(blog);
//            updateById(blog);
            updateById(blog);

        }

        return new ApiResponse("提交成功");
    }

    @Override
    public ApiResponse<BlogVO> getBlogs(BlogOfProblemPO po) {
        Page<Blog> page = new Page<>(po.getPageNumber(), po.getPageSize());

        Integer countAll = count( new LambdaQueryWrapper<Blog>().eq(po.getGroupName() != null, Blog::getGroupName, po.getGroupName())
                .eq(po.getId()!=null,Blog::getProId,po.getId())
                .isNull(po.getIsSelect()!=null&&po.getIsSelect() == 0, Blog::getScore).isNotNull(po.getIsSelect()!=null&&po.getIsSelect() == 1, Blog::getScore)
                .like(po.getProName()!=null,Blog::getProName,po.getProName())
                .like(po.getGroupName() != null, Blog::getGroupName, po.getGroupName()).like(po.getSendTime() != null, Blog::getGmtCreate, po.getSendTime())
                .orderByDesc(Blog::getGmtCreate)
        );

        Page<Blog> page1 = page(page, new LambdaQueryWrapper<Blog>().eq(po.getGroupName() != null, Blog::getGroupName, po.getGroupName())
                .eq(po.getId()!=null,Blog::getProId,po.getId())
                .isNull(po.getIsSelect()!=null&&po.getIsSelect() == 0, Blog::getScore).isNotNull(po.getIsSelect()!=null&&po.getIsSelect() == 1, Blog::getScore)
                .like(po.getProName()!=null,Blog::getProName,po.getProName())
                .like(po.getGroupName() != null, Blog::getGroupName, po.getGroupName()).like(po.getSendTime() != null, Blog::getGmtCreate, po.getSendTime())
                .orderByDesc(Blog::getGmtCreate)
        );
        List<BlogVO> blogVOS = BeanUtil.listA2ListB(page1.getRecords(), BlogVO.class);
        blogVOS.stream().forEach(t->{
           t.setProLink(problemService.getById(t.getProId()).getLink());
           t.setCreated(t.getGmtCreate().toLocalDate().toString());
        });

        return new ApiResponse(blogVOS,new Long(blogVOS.size()),countAll);
    }
}

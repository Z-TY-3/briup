package com.briup.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.Message;
import com.briup.demo.util.MessageUtil;
import com.briup.demo.util.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 与文章相关的和前端交互的web层  swagger接口
 * @author hero
 *
 */

@RestController
@Api(description="文章相关接口")
public class ArticleController {
		@Autowired 
		private IArticleService iArticleService;
		
		@PostMapping("/addArticle")
		@ApiOperation("添加文章")
		public Message<String> saveArticle(Article article){
				try {
					iArticleService.saveOrUpdateArticle(article);
					return MessageUtil.success();
				} catch (CustomerException e) {
					return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "空文章错误:"+e.getMessage());
				}
		}
		
		@PostMapping("/updateArticle")
		@ApiOperation("更新文章")
		public Message<String> updateArticle(Article article){
			
				try {
					iArticleService.saveOrUpdateArticle(article);
					return MessageUtil.success();
				} catch (CustomerException e) {
					return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "空文章错误:"+e.getMessage());
				}
		}
		
		
		@GetMapping("/deleteArticle")
		@ApiOperation("根据id删除文章")
		public Message<String> deleteArticle(int id){
				//用int 无所谓  删除0   删除空   前台后台可以控制  数据验证
				iArticleService.deleteArticleById(id);
				return MessageUtil.success();
					
		}
		
		
		
		
		@GetMapping("/getArticleByCondition/{keystr}/{condition}")
		@ApiOperation(value="根据条件查找文章",notes="根据条件查找文章")// @ApiParam("搜索的关键字")
		//
//		@ApiImplicitParams({ 
//			@ApiImplicitParam(name = "keystr",
//				 value = "关键字", 
//				 paramType = "path",required=false), 
//			@ApiImplicitParam(name = "condition",
//				 value = "栏目名字", 
//				 paramType = "path",required=false)})
		public Message<List<Article>> getArticleByCondition(
//				@ApiParam(name = "keystr", value = "关键字")
//				PathVariable String keystr,
//				@PathVariable(value = "keystr")
				
				String keystr,
//				@PathVariable(value = "condition") 
				String condition){
			try {
				System.out.println(//这个数据是从url请求路径拿的
						iArticleService.findArticalByCondition(keystr, condition));
				
				return MessageUtil.success(
						iArticleService.findArticalByCondition(keystr, condition));
			} catch (CustomerException e) {
				return MessageUtil.error(
						StatusCodeUtil.ERROR_CODE, "系统错误:"+e.getMessage());
				
			}
			
		}
		
		
		@GetMapping("/getArticleById")
		@ApiOperation("根据id查找文章")
		public Message<Article> getArticleById(int Id){
			
			
				return MessageUtil.success(
						iArticleService.findArticleById(Id));
		}
		
		
	
}

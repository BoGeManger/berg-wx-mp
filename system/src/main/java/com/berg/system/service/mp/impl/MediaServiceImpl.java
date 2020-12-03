package com.berg.system.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MediaTbl;
import com.berg.dao.system.mp.service.MediaTblDao;
import com.berg.exception.FailException;
import com.berg.system.auth.JWTUtil;
import com.berg.system.service.mp.MediaService;
import com.berg.vo.mp.MediaVo;
import com.berg.vo.mp.in.GetMediaPageInVo;
import com.berg.vo.mp.in.MpMaterialNewsUpdateInVo;
import com.berg.vo.mp.in.MpMaterialNewsUploadInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.mp.bean.material.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    MediaTblDao mediaTblDao;
    @Autowired
    MediaAsyncTask mediaAsyncTask;

    /**
     *  获取素材分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MediaVo> getMediaPage(GetMediaPageInVo input){
        return mediaTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MediaTbl>().
                    eq(MediaTbl::getAppId,input.getAppId());
            return mediaTblDao.list(query,MediaVo.class);
        });
    }

    /**
     * 上传临时素材
     * @param appId
     * @param mediaType
     * @param multipartFile
     */
    @Override
    public void mediaUpload(String appId,String mediaType,String remark,MultipartFile multipartFile){
        File file = null;
        String operator = jwtUtil.getUsername();
        try{
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], "."+filename[1]);
            multipartFile.transferTo(file);
            WxMediaUploadResult wxMediaUploadResult = WxMpUtil.getService(appId).getMaterialService().mediaUpload(mediaType,file);
            //新增记录
            mediaAsyncTask.addMedia(appId,wxMediaUploadResult.getMediaId(),wxMediaUploadResult.getUrl()
                    ,wxMediaUploadResult.getCreatedAt(),mediaType,0,remark,operator);
        }catch (Exception ex){
            throw new FailException("调用公众号上传临时素材接口mediaUpload失败:"+ex.getMessage());
        }finally {
            file.deleteOnExit();
        }
    }

    /**
     * 上传图片永久素材
     * @param appId
     * @param multipartFile
     */
    @Override
    public void mediaImgUpload(String appId,String remark,MultipartFile multipartFile){
        File file = null;
        String operator = jwtUtil.getUsername();
        try{
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], "."+filename[1]);
            multipartFile.transferTo(file);
            WxMediaImgUploadResult wxMediaImgUploadResult = WxMpUtil.getService(appId).getMaterialService().mediaImgUpload(file);
            //新增记录
            mediaAsyncTask.addMedia(appId,null,wxMediaImgUploadResult.getUrl()
                    ,null,"image",0,remark,operator);
        }catch (Exception ex){
            throw new FailException("调用公众号上传图片永久素材接口mediaImgUpload失败:"+ex.getMessage());
        }finally {
            file.deleteOnExit();
        }
    }

    /**
     * 上传文件永久素材
     * @param appId
     * @param mediaType
     * @param name
     * @param videoTitle
     * @param videoIntroduction
     * @param multipartFile
     */
    @Override
    public void materialFileUpload(String appId,String mediaType,String name,String videoTitle,String videoIntroduction,String remark,MultipartFile multipartFile){
        File file = null;
        String operator = jwtUtil.getUsername();
        try{
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], "."+filename[1]);
            multipartFile.transferTo(file);
            WxMpMaterial wxMpMaterial = new WxMpMaterial();
            wxMpMaterial.setFile(file);
            wxMpMaterial.setName(name);
            wxMpMaterial.setVideoTitle(videoTitle);
            wxMpMaterial.setVideoIntroduction(videoIntroduction);
            WxMpMaterialUploadResult wxMpMaterialUploadResult = WxMpUtil.getService(appId).getMaterialService().materialFileUpload(mediaType,wxMpMaterial);
            //新增记录
            mediaAsyncTask.addMedia(appId,wxMpMaterialUploadResult.getMediaId(),wxMpMaterialUploadResult.getUrl()
                    ,null,mediaType,0,remark,operator);
        }catch (Exception ex){
            throw new FailException("调用公众号上传文件永久素材接口materialFileUpload失败:"+ex.getMessage());
        }finally {
            file.deleteOnExit();
        }
    }

    /**
     * 上传图文永久素材
     * @param input
     */
    @Override
    public void materialNewsUpload(MpMaterialNewsUploadInVo input){
        String operator = jwtUtil.getUsername();
        try{
            WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
            List<WxMpNewsArticle> articles = new ArrayList<>();
            input.getArticles().forEach(item->{
                WxMpNewsArticle wxMpNewsArticle = new WxMpNewsArticle();
                BeanUtils.copyProperties(item,wxMpNewsArticle);
                articles.add(wxMpNewsArticle);
            });
            wxMpMaterialNews.setArticles(articles);
            WxMpMaterialUploadResult wxMpMaterialUploadResult = WxMpUtil.getService(input.getAppId()).getMaterialService().materialNewsUpload(wxMpMaterialNews);
            //新增记录
            mediaAsyncTask.addMedia(input.getAppId(),wxMpMaterialUploadResult.getMediaId(),wxMpMaterialUploadResult.getUrl()
                    ,null,"news",0,input.getRemark(),operator);
        }catch (Exception ex){
            throw new FailException("调用公众号上传图文永久素材接口materialNewsUpload失败:"+ex.getMessage());
        }
    }

    /**
     * 修改永久素材
     * @param input
     * @return
     */
    @Override
    public Boolean materialNewsUpdate(MpMaterialNewsUpdateInVo input){
        Boolean flag = false;
        String operator = jwtUtil.getUsername();
        try{
            WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate = new WxMpMaterialArticleUpdate();
            BeanUtils.copyProperties(input,wxMpMaterialArticleUpdate);
            flag = WxMpUtil.getService(input.getAppId()).getMaterialService().materialNewsUpdate(wxMpMaterialArticleUpdate);
            //修改记录
            mediaAsyncTask.updateMedia(input.getAppId(),input.getMediaId(),operator);
        }catch (Exception ex){
            throw new FailException("调用公众号修改永久素材接口materialNewsUpdate失败:"+ex.getMessage());
        }
        return flag;
    }

    /**
     * 删除永久素材
     * @param appId
     * @param mediaId
     * @return
     */
    @Override
    public Boolean materialDelete(String appId,String mediaId){
        Boolean flag = false;
        String operator = jwtUtil.getUsername();
        try{
            flag = WxMpUtil.getService(appId).getMaterialService().materialDelete(mediaId);
            //删除记录
            mediaAsyncTask.delMedia(appId,mediaId,operator);
        }catch (Exception ex){
            throw new FailException("调用公众号删除永久素材接口materialDelete失败:"+ex.getMessage());
        }
        return flag;
    }
}

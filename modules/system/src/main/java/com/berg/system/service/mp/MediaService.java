package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MediaVo;
import com.berg.vo.mp.in.GetMediaPageInVo;
import com.berg.vo.mp.in.MpMaterialNewsUpdateInVo;
import com.berg.vo.mp.in.MpMaterialNewsUploadInVo;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    PageInfo<MediaVo> getMediaPage(GetMediaPageInVo input);

    void mediaUpload(String appId, String mediaType, String remark, MultipartFile multipartFile);

    void mediaImgUpload(String appId,String remark,MultipartFile multipartFile);

    void materialFileUpload(String appId,String mediaType,String name,String videoTitle,String videoIntroduction,String remark,MultipartFile multipartFile);

    void materialNewsUpload(MpMaterialNewsUploadInVo input);

    Boolean materialNewsUpdate(MpMaterialNewsUpdateInVo input);

    Boolean materialDelete(String appId,String mediaId);
}

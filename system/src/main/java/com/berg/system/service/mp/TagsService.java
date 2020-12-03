package com.berg.system.service.mp;

import com.berg.vo.mp.MpUserTagVo;
import com.berg.vo.mp.in.MpBatchTagInVo;

import java.util.List;

public interface TagsService {

    List<MpUserTagVo> getTagsList(String appId);

    Boolean batchTagging(MpBatchTagInVo input);

    Boolean batchUntagging(MpBatchTagInVo input);
}

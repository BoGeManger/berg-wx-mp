package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.KeysReplyEditVo;
import com.berg.vo.mp.KeysReplyVo;
import com.berg.vo.mp.in.GetKeysReplyPageInVo;

public interface KeysReplyService {

    PageInfo<KeysReplyVo> getKeysReplyPage(GetKeysReplyPageInVo input);

    KeysReplyEditVo getKeysReply(Integer id);

    Integer addKeysReply(KeysReplyEditVo input);

    Integer updateKeysReply(KeysReplyEditVo input);

    void delKeysReply(Integer id);
}

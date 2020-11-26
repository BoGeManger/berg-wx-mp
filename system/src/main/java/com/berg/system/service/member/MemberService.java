package com.berg.system.service.member;

import com.berg.dao.page.PageInfo;
import com.berg.vo.member.MemberVo;
import com.berg.vo.member.MpBindVo;
import com.berg.vo.member.in.GetMemberPageInVo;
import com.berg.vo.member.in.GetMpBindPageInVo;

public interface MemberService {

    PageInfo<MemberVo> getMemberPage(GetMemberPageInVo input);

    PageInfo<MpBindVo> getMpBindPage(GetMpBindPageInVo input);
}

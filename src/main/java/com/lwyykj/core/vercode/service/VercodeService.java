package com.lwyykj.core.vercode.service;

import com.lwyykj.core.bean.utils.Vercode;

public interface VercodeService {
	Vercode selectByTel(String tel);
}

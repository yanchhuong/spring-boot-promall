package com.code.model;

import java.util.List;

import com.code.comm.PagingUtils;

public class MUserListOut_R002 {
	private List<MUserListOut_R001> OutRec;
	private PagingUtils pagination;
	public List<MUserListOut_R001> getOutRec() {
		return OutRec;
	}
	public void setOutRec(List<MUserListOut_R001> outRec) {
		OutRec = outRec;
	}
	public PagingUtils getPagination() {
		return pagination;
	}
	public void setPagination(PagingUtils pagination) {
		this.pagination = pagination;
	}

}

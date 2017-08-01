package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PopupViewController {
	@RequestMapping(value="/popup_uploadimg_001",method = RequestMethod.GET)
	public String popupUploadImg001() {
		return "popup/popup_uploadimg_001_view";
	}
	@RequestMapping(value="/popup_uploadimg_002",method = RequestMethod.GET)
	public String popupUploadImg002() {
		return "popup/popup_uploadimg_002_view";
	}
	@RequestMapping(value="/popup_add_role_list_001",method = RequestMethod.GET)
	public String popupUploadRoleAddList() {
		return "popup/popup_add_role_list_001_view";
	}
	@RequestMapping(value="/popup_user_settingrole_001",method = RequestMethod.GET)
	public String popupUserSettingRole() {
		return "popup/popup_user_settingrole_001_view";
	}

}

package snod.com.cn.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import snod.com.cn.server.WebSocketServer;
import snod.com.cn.utils.EcpStackTrace;
import snod.com.cn.utils.ResultInfo;
import snod.com.cn.utils.ResultTools;

@RestController
public class CheckCenterController {
		//页面请求
		@GetMapping("/socket/{cid}")
		public ModelAndView socket(@PathVariable String cid) {
			ModelAndView mav=new ModelAndView("/login");
			mav.addObject("cid", cid);
			return mav;
		}
		//页面请求
		@GetMapping("/test")
		public ModelAndView socket() {
			ModelAndView mav=new ModelAndView("/test1");
//			mav.addObject("cid", cid);
			return mav;
		}
		//推送数据接口
		@RequestMapping("/socket/push/{cid}")
		public ResultInfo pushToWeb(@PathVariable String cid,String message) {  
			try {
				WebSocketServer.sendInfo(message,cid);
			}catch (IOException e) {
				e.printStackTrace();
				return ResultTools.result(ResultTools.ERROR_CODE_404, EcpStackTrace.getExceptionStackTrace(e), null);
			}  
			return ResultTools.result(ResultTools.ERROR_CODE_0, null,null);
		} 
		//推送数据接口
		@RequestMapping("/socket/push")
		public ResultInfo pushToWeb(String message) {  
			try {
				WebSocketServer.sendInfo(message,null);
			}catch (IOException e) {
				e.printStackTrace();
				return ResultTools.result(ResultTools.ERROR_CODE_404, EcpStackTrace.getExceptionStackTrace(e), null);
			}  
			return ResultTools.result(ResultTools.ERROR_CODE_0, null,null);
		} 

}

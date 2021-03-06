package com.dy.controller;  
  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dy.model.Advice;
import com.dy.model.Business;
import com.dy.model.Case;
import com.dy.model.Doctor;
import com.dy.model.Evaluate;
import com.dy.model.Fee;
import com.dy.model.Manager;
import com.dy.model.Menu;
import com.dy.model.Order;
import com.dy.model.Patient;
import com.dy.model.Subject;
import com.dy.model.User;
import com.dy.pub.PubUtil;
import com.dy.service.AdviceService;
import com.dy.service.CaseService;
import com.dy.service.DoctorService;
import com.dy.service.EvaluateService;
import com.dy.service.FeeService;
import com.dy.service.ManagerService;
import com.dy.service.MedicinalService;
import com.dy.service.OrderService;
import com.dy.service.PatientService;
import com.dy.service.ProfessionService;
import com.dy.service.SubjectService;
import com.dy.service.UserService;
import com.dy.util.DataUtils;
import com.dy.util.UtilEmpty;
  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private UserService userService;  
    @Resource  
    private MedicinalService medicinalrService;  
    
    @Resource  
    private ManagerService managerService; 
    @Resource  
    private PatientService patientService; 
    @Resource  
    private DoctorService doctorService; 
    @Resource  
    private OrderService orderService; 
    @Resource  
    private SubjectService subjectService; 
    @Resource  
    private CaseService caseService; 
    @Resource  
    private AdviceService adviceService; 
    @Resource  
    private ProfessionService professionService; 
    @Resource  
    private EvaluateService evaluateService; 
    @Resource  
    private FeeService feeService; 
     //鐢ㄦ埛娉ㄥ唽
    @RequestMapping("/userRegister")  
    @ResponseBody
    public JSONObject userRegister(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	String usercate = request.getParameter("user");
    	User user = new User();
    	user.setUsername(name);
    	user.setPassword(password);
    	userService.insertUser(user);
    	if("1".equals(usercate)){
    		Patient patient = new Patient();
    		patient.setUserid(user.getUserid());
    		patientService.insertPatient(patient);
    		
    		
    	}else if("3".equals(usercate)){
    		Doctor doc = new Doctor();
    		doc.setUserid(user.getUserid());
    		doctorService.insertDoctor(doc);
    	}
    	json.put("msg","注册成功");
    	 return json;
    }  
//    用户登录
    @RequestMapping("/userLogin")  
    public String userLogin(HttpServletRequest request,Model model){  
//    	List<Business> blist = PubUtil.getListbusiness();
//    	List<Menu> mlist = PubUtil.getListmenu();
//    	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//    	System.out.println(blist.size()); 
//    	System.out.println(mlist.size());
//    	System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    	User usertemp = new User();
    	String usercate = request.getParameter("usercate");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
//    	if(!UtilEmpty.isNullorEmpty(password) && UtilEmpty.isNullorEmpty(name)){
//    		usertemp.setPassword(password);
//    		usertemp.setUsername(name);
//    		User user = userService.getUser(usertemp);
//    		if(!UtilEmpty.isNullorEmpty(patientService.selectPatientByUserid(user.getUserid()))){
//    			request.getSession().setAttribute("usercate", usercate);
//    			request.getSession().setAttribute("currUserid", user.getUserid());
//    			request.getSession().setAttribute("currusername", user.getUsername());
//    			request.getSession().setAttribute("role", "P");
//    			return "main-patient";
//    		}
//    		if(!UtilEmpty.isNullorEmpty(doctorService.selectByuserid(user.getUserid()))){
//    			request.getSession().setAttribute("usercate", usercate);
//    			request.getSession().setAttribute("currUserid", user.getUserid());
//    			request.getSession().setAttribute("currusername", user.getUsername());
//    			request.getSession().setAttribute("role", "D");
//    			return "main-doc";
//    		}
//    		if(!UtilEmpty.isNullorEmpty(managerService.getManagerById(user.getUserid()))){
//    			request.getSession().setAttribute("usercate", usercate);
//    			request.getSession().setAttribute("currUserid", user.getUserid());
//    			request.getSession().setAttribute("currusername", user.getUsername());
//    			request.getSession().setAttribute("role", "M");
//    			return "main-manager";
//    		}
//    	}
    	HttpSession session = request.getSession();
    	if("1".equals(usercate)){
    		usertemp.setPassword(password);
    		usertemp.setUsername(name);
    		User user = userService.getUser(usertemp);
    		if(null!=patientService.selectPatientByUserid(user.getUserid())){
    			session.setAttribute("usercate", usercate);
    			session.setAttribute("currUserid", user.getUserid());
    			session.setAttribute("currusername", user.getUsername());
    			request.getSession().setAttribute("role", "P");
    			return "main-patient";
    		}
    	}else if("3".equals(usercate)){
    		usertemp.setPassword(password);
    		usertemp.setUsername(name);
    		User user = userService.getUser(usertemp);
    		if(null!=doctorService.selectByuserid(user.getUserid())){
    			session.setAttribute("usercate", usercate);
    			session.setAttribute("currUserid", user.getUserid());
    			session.setAttribute("currusername", user.getUsername());
    			request.getSession().setAttribute("role", "D");
    			return "main-doc";
    		}
    	}
    	
    	if("2".equals(usercate)){
    		usertemp.setPassword(password);
    		usertemp.setUsername(name);
    		User user = userService.getUser(usertemp);
    		if(null!=user){
    			session.setAttribute("usercate", usercate);
    			session.setAttribute("currUserid", user.getUserid());
    			session.setAttribute("currusername", user.getUsername());
    			request.getSession().setAttribute("role", "M");
    			return "main-manager";
    		}
    	}
    	
    	return "error";  
    }  
    //用户预约
    @RequestMapping("/userLogout")  
    public String userLogout(HttpServletRequest request,Model model){
    	return "logout";  
    }  
    @RequestMapping("/userOrder")  
    public String userOrder(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Order order = new Order();
    	order.setPatientid(patient.getPatientid());
    	List<Order> orderlist = orderService.searchOrder(order);
    	List<Map> ordermap = new ArrayList<Map>();
    	for(Order ord:orderlist){
    		Map map =new HashMap();
    		map.put("orderid", ord.getOrderid());
    		map.put("doctorname", doctorService.searchDoctorsByOrder(ord).getRealname());
    		map.put("subject", subjectService.selectByPrimaryKey(ord.getSubjectid()).getSubjectname());
    		map.put("orderdate", ord.getOrderdate());
    		map.put("orderstate", ord.getOrderstate());
    		ordermap.add(map);
    	}
    	model.addAttribute("orderlist", ordermap);
    	
        return "patient-order";  
    }  
    //用户新增预约
    @RequestMapping("/userOrderAdd")  
    public String userOrderAdd(HttpServletRequest request,Model model){
    	List<Subject> subjectList = subjectService.selectAllSubject();
    	model.addAttribute("subjectList", subjectList);
    	return "patient-order-add";  
    }  
    
    @RequestMapping("/userOrderAddSubmit") 
    @ResponseBody
    public JSONObject userOrderAddSubmit(HttpServletRequest request,Model model){
    	JSONObject json = new JSONObject();
    	Integer subjectid = Integer.parseInt(request.getParameter("subjectid"));
    	Integer doctorid = Integer.parseInt(request.getParameter("doctorid"));
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	String orderdate = request.getParameter("orderdate");
    	Order order = new Order();
    	order.setDoctorid(doctorid);
    	order.setSubjectid(subjectid);
    	order.setOrderdate(orderdate);
    	order.setPatientid(patient.getPatientid());
    	order.setOrderstate("待发起");
    	orderService.insertOrder(order);
    	json.put("msg", "预约成功");
    	return json;  
    }  
  //用户新增预约
    @RequestMapping("/updateOrder")  
    @ResponseBody
    public JSONObject updateOrder(HttpServletRequest request,Model model){
    	JSONObject json = new JSONObject();
    	Integer orderid = Integer.parseInt(request.getParameter("orderid"));
    	Order order = orderService.getOrderById(orderid);
    	order.setOrderstate("已发起");
    	orderService.updateOrder(order);
    	json.put("msg", "预约已发起");
    	return json;  
    } 
    @RequestMapping("/getDoctors")  
    @ResponseBody
    public JSONObject getDoctors(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	Integer subjectid = Integer.parseInt(request.getParameter("subject"));
    	Doctor doc = new Doctor();
    	doc.setSubjectid(subjectid);
    	List<Doctor> doctorList = doctorService.searchDoctors(doc);
    	json.put("doctor",json.toJSON(doctorList));
    	 return json;
    } 
    
    @RequestMapping("/userCaseQuery")  
    public String userCaseQuery(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Case cass = new Case();
    	cass.setPatientid(patient.getPatientid());
    	List<Case> caselist = caseService.selectCaseList(cass);
    	List<Map> listmap = new ArrayList<Map>();
    	if(null!=caselist){
    		for(Case cas:caselist){
    			Map map =new HashMap();
    			map.put("caseid", cas.getCaseid());
    			map.put("orderid", cas.getOrderid());
    			map.put("adviceid", cas.getAdivceid());
    			map.put("doctorid", cas.getDoctorid());
    			map.put("doctorname", doctorService.selectByPrimaryKey(cas.getDoctorid()).getRealname());
    			map.put("subjectname", subjectService.selectByPrimaryKey(cas.getSubjectid()).getSubjectname());
    			map.put("visitdate", cas.getVisitdate());
    			map.put("diagnosis", cas.getDiagnosis());
    			map.put("treat", cas.getTreat());
    			listmap.add(map);
    		}
    	}
    	model.addAttribute("caselist", listmap);
    	return "patient-case-query";  
    } 
    @RequestMapping("/userDoctorAdvice")  
    public String userDoctorAdvice(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Advice advice = new Advice();
    	advice.setPatientid(patient.getPatientid());
    	List<Advice> advicelist = adviceService.selectAdvicesList(advice);
    	List<Map> listmap = new ArrayList<Map>();
    	if(advicelist.size()>0){
    		for(Advice adv:advicelist){
    			Map map = new HashMap();
    			map.put("adviceid", adv.getAdviceid());
    			map.put("patientid", adv.getPatientid());
    			map.put("doctorid", adv.getDoctorid());
    			map.put("doctorname", doctorService.selectByPrimaryKey(adv.getDoctorid()).getRealname());
    			map.put("subjectname", subjectService.selectByPrimaryKey(adv.getSubjectid()).getSubjectname());
    			map.put("adivcecontent", adv.getAdvicecontent());
    			map.put("advicedate", adv.getAdvicedate());
    			map.put("adviceanswer", adv.getAdviceanswer());
    			map.put("adviceanswerdate", adv.getAdviceanswerdate());
    			listmap.add(map);
    		}
    	}
    	model.addAttribute("advicelist", listmap);
    	return "patient-advice-query";  
    } 
    @RequestMapping("/userDoctorAdviceAdd")  
    public String userDoctorAdviceAdd(HttpServletRequest request,Model model){
    	List<Doctor> listdoctor = doctorService.searchAllDoctors();
    	List<Map> listmap = new ArrayList<Map>();
    	if(null != listdoctor){
    		for(Doctor doc:listdoctor){
    			Map map = new HashMap();
    			map.put("doctorid", doc.getDoctorid());
    			map.put("doctorname", doc.getRealname());
    			map.put("sex", doc.getSex());
    			map.put("subjectname", subjectService.selectByPrimaryKey(doc.getSubjectid()).getSubjectname());
    			map.put("professionname", professionService.selectByPrimaryKey(doc.getProfessionid()).getProfessionname());
    			map.put("goodat", doc.getGoodat());
    			listmap.add(map);
    		}
    	}
    	model.addAttribute("doctorlist", listmap);
    	return "patient-advice-add";  
    } 
    @RequestMapping("/userDoctorAdviceAddInput")  
    public String userDoctorAdviceAddInput(HttpServletRequest request,Model model){
    	Integer doctorid = Integer.parseInt(request.getParameter("doctorid"));
    	Doctor doctor = doctorService.selectByPrimaryKey(doctorid);
    	Map map = new HashMap();
    	map.put("doctorid", doctor.getDoctorid());
    	map.put("doctorname", doctor.getRealname());
    	map.put("subjectname", subjectService.selectByPrimaryKey(doctor.getSubjectid()).getSubjectname());
    	model.addAttribute("doctor", map);
    	return "patient-advice-add-input";  
    } 
    
    @RequestMapping("/userDoctorAdviceAddSubmit")  
    @ResponseBody
    public JSONObject userDoctorAdviceAddSubmit(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Integer doctorid = Integer.parseInt(request.getParameter("doctorid"));
    	String advicecontent = request.getParameter("advicecontent");
    	Advice advice = new Advice();
    	advice.setPatientid(patient.getPatientid());
    	advice.setDoctorid(doctorid);
    	advice.setSubjectid(doctorService.selectByPrimaryKey(doctorid).getSubjectid());
    	advice.setAdvicecontent(advicecontent);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	advice.setAdvicedate(sdf.format(new Date()));
    	advice.setIsanswer("N");
    	adviceService.insertSelective(advice);
    	json.put("msg", "咨询新增成功");
    	 return json;
    } 
    
    @RequestMapping("/userDoctorEvaluate")  
    public String userDoctorEvaluate(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Case cass = new Case();
    	cass.setPatientid(patient.getPatientid());
    	List<Case> caselist = caseService.selectCaseList(cass);
    	List<Map> listmap = new ArrayList<Map>();
    	if(null != caselist){
    		for(Case ca:caselist){
    			Map map = new HashMap();
    			Doctor doctor = doctorService.selectByPrimaryKey(ca.getDoctorid());
    			map.put("doctorid", doctor.getDoctorid());
    			map.put("doctorname", doctor.getRealname());
    			map.put("subjectname",subjectService.selectByPrimaryKey(doctor.getSubjectid()).getSubjectname());
    			map.put("summary", doctor.getSummary());
    			listmap.add(map);
    		}
    	}
    	model.addAttribute("doctorlist", listmap);
    	return "patient-doc-evaluate";  
    } 
    @RequestMapping("/userDoctorEvaluateAdd")  
    public String userDoctorEvaluateAdd(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Integer doctorid = Integer.parseInt(request.getParameter("doctorid"));
    	Doctor doctor = doctorService.selectByPrimaryKey(doctorid);
    	Map map = new HashMap();
    	map.put("doctorid", doctor.getDoctorid());
    	map.put("doctorname", doctor.getRealname());
    	map.put("subjectname",subjectService.selectByPrimaryKey(doctor.getSubjectid()).getSubjectname());
    	map.put("goodat", doctor.getGoodat());
    	map.put("summary", doctor.getSummary());
    	model.addAttribute("doctor", map);
    	return "patient-doc-evaluate-add";  
    } 
    
    @RequestMapping("/userDoctorEvaluateAddSubmit")  
    @ResponseBody
    public JSONObject userDoctorEvaluateAddSubmit(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	Integer doctorid = Integer.parseInt(request.getParameter("doctorid"));
    	String evaluatelevel = request.getParameter("evaluatelevel");
    	String evaluatecontent = request.getParameter("evaluatecontent");
    	Evaluate eva = new Evaluate();
    	eva.setDoctorid(doctorid);
    	eva.setPatientid(patient.getPatientid());
    	eva.setEvaluatelevel(evaluatelevel);
    	eva.setEvaluatecontent(evaluatecontent);
    	eva.setEvaluatedate(DataUtils.formatStringDate(new Date()));
    	evaluateService.insertSelective(eva);
    	json.put("msg","评价新增成功");
    	 return json;
    }
    
    @RequestMapping("/userPatientPasswordSet")  
    public String userPatientPasswordSet(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	User user = userService.getUserById(userid);
    	model.addAttribute("user", user);
    	return "patient-password-set";  
    } 
    @RequestMapping("/userPatientPasswordUpdate")  
    @ResponseBody
    public JSONObject userPatientPasswordUpdate(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	String password= request.getParameter("password");
    	User user = new User();
    	user.setUserid(userid);
    	user.setPassword(password);
    	userService.updateByPrimaryKeySelective(user);
    	json.put("msg","密码修改成功");
    	return json;
    }
    @RequestMapping("/userPatientInfoSet")  
    public String userPatientInfoSet(HttpServletRequest request,Model model){
    	Integer userid = (Integer) request.getSession().getAttribute("currUserid");
    	Patient patient = patientService.selectPatientByUserid(userid);
    	User user = userService.getUserById(userid);
    	model.addAttribute("patient", patient);
    	model.addAttribute("user", user);
    	return "patient-info-set";  
    } 
    @RequestMapping("/userPatientInfoUpdate")  
    @ResponseBody
    public JSONObject userPatientInfoUpdate(HttpServletRequest request,Model model){  
    	JSONObject json = new JSONObject();
    	Integer patientid = Integer.parseInt(request.getParameter("patientid"));
    	String sex= request.getParameter("sex");
    	String brith= request.getParameter("brith");
    	String realname= request.getParameter("realname");
    	Integer weight= Integer.parseInt(request.getParameter("weight"));
    	String nation= request.getParameter("nation");
    	String idcardnum= request.getParameter("idcardnum");
    	String address= request.getParameter("address");
    	String phonenum= request.getParameter("phonenum");
    	String anaphylactogen= request.getParameter("anaphylactogen");
    	Patient patient = new Patient();
    	patient.setPatientid(patientid);
    	patient.setSex(sex);
    	patient.setBrith(brith);
    	patient.setName(realname);
    	patient.setWeight(weight);
    	patient.setNation(nation);
    	patient.setIdcardnum(idcardnum);
    	patient.setAddress(address);
    	patient.setPhonenum(phonenum);
    	patient.setAnaphylactogen(anaphylactogen);
    	patientService.updateByPrimaryKeySelective(patient);
    	json.put("msg","修改成功");
    	 return json;
    }
    @RequestMapping("/userPatientFeeQuery")  
    public String userPatientFeeQuery(HttpServletRequest request,Model model){
    	Integer caseid = Integer.parseInt(request.getParameter("caseid"));
    	Fee fee = new Fee();
    	fee.setCaseid(caseid);
    	List<Fee> feelist = feeService.selectFeeList(fee);
    	List<Map> listmap = new ArrayList<Map>();
    	if(null!=feelist){
    		for(Fee fe:feelist){
    			Map map = new HashMap();
        		map.put("feeid", fe.getFeeid());
        		map.put("caseid", fe.getCaseid());
        		map.put("feecategory", fe.getFeecategory());
        		map.put("feemedicinalid", fe.getMedicinalid());
        		map.put("feemedicinalname", medicinalrService.selectByPrimaryKey(fe.getMedicinalid()).getMedicinalname());
        		map.put("feeprice", medicinalrService.selectByPrimaryKey(fe.getMedicinalid()).getMedicinalprice());
        		map.put("feemedicinalnum", fe.getMedicinalnum());
        		listmap.add(map);
    		}
    	}
    	model.addAttribute("feelist", listmap);
    	return "patient-fee-query";  
    } 
      
  /*  @RequestMapping("/addUser")  
    public String addUser(HttpServletRequest request,Model model){  
        User user = new User();  
        user.setPassword(String.valueOf(request.getParameter("password")));  
        return "redirect:/user/userList";  
    }  */
}
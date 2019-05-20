package model.services;

import java.util.ArrayList;
import java.util.List;

public class ServiceOperationResult <T>{
	
	private ServiceOpertationResultType resultType;
	private String resultMsg;
	private List<T> queryResluts;
	
	public ServiceOperationResult(ServiceOpertationResultType resultType, String resultMsg, List<T> queryResluts) {
		this.resultType = resultType;
		this.resultMsg = resultMsg;
		this.queryResluts= queryResluts;
	}
	
	public ServiceOperationResult(ServiceOpertationResultType resultType, String resultMsg) {
		this(resultType,resultMsg,new ArrayList<T>());
	}

	public ServiceOpertationResultType getResultType() {
		return resultType;
	}

	public void setResultType(ServiceOpertationResultType resultType) {
		this.resultType = resultType;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public List<T> getQueryResluts() {
		return queryResluts;
	}

	public void setQueryResluts(List<T> queryResluts) {
		this.queryResluts = queryResluts;
	}

}

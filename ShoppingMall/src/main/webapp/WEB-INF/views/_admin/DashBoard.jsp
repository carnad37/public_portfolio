<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    <div id="content-wrapper">
      <div class="container-fluid">
        <!-- Icon Cards-->
        <div class="row">
          <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-primary o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
                  <i class="fas fa-fw fa-comments"></i>
                </div>
                <div class="mr-5">대기중인<br/>상담 내역</div>
              </div>
              <a class="card-footer text-white clearfix small z-1" href="<c:url value="/board/QnA_view"/>">
                <span class="float-left">자세히 보기</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-warning o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
<!--                   <i class="fas fa-fw fa-list"></i> -->
					<i class="fas fa-fw fa-cart-plus"></i>
                </div>
                <div class="mr-5">대기중인<br/>거래 내역</div>
              </div>
              <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left">자세히 보기</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-success o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
                  <i class="fas fa-fw fa-shopping-cart"></i>
                </div>
                <div class="mr-5">진행중인<br/>거래 내역</div>
              </div>
              <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left">자세히 보기</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-danger o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
<!--                   <i class="fas fa-fw fa-life-ring"></i> -->
					<i class="fas fa-fw fa-cash-register"></i>
                </div>
                <div class="mr-5">오늘 완료된<br/>거래 내역</div>
              </div>
              <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left">자세히 보기</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
        </div>

        <!-- Area Chart Example-->
        <div class="row">
        <div class="col-lg-6 mb-6">
          <div class="card">
           <div class="card-header">
            <i class="fas fa-chart-area"></i>
           	공지사항
           </div>
          <div class="card-body">
<%--             <canvas id="myAreaChart" width="100%" height="30"></canvas> --%>
				테스트	
          </div>
          </div>

<!--           <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
        </div>
        
       <div class="col-lg-6 mb-6">
          <div class="card">       
          <div class="card-header">
            <i class="fas fa-chart-area"></i>
                            상담/질문
           </div>
          <div class="card-body">
<%--             <canvas id="myAreaChart" width="100%" height="30"></canvas> --%>
				테스트	
          </div>
<!--           <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
		</div>
        </div>
        </div>
        <div class="w-100 shadow p-4" style="height: 500px;">
        	<h3>본 페이지는 관리자페이지입니다.</h3>
        	<hr>
        	<div class="d-table w-100 h-75">
        		<h2 class="d-table-cell text-center align-middle text-primary font-weight-bold">제작중&nbsp;<i class="fas fa-spinner fa-spin"></i></h2>
        	</div>
        </div>

      </div>
      <!-- /.container-fluid -->
  </div>


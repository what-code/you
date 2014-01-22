<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage="" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="margin: 30px auto">
	<div class="page clear-fix">
		<div>
			<c:choose>
				<c:when test="${pageList.totalPages>0}">
					<!-- 首页 -->
					<c:choose>
						<c:when test="${searchDto.currPageNo == 1}"><a href="javascript:void('0');" class="first dis">首页</a></c:when>
						<c:otherwise><a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_1.html" class="first">首页</a></c:otherwise>
					</c:choose>
					<!-- 上一页 -->
					<c:choose>
						<c:when test="${pageList.hasPrevPage}"><a class="prev" href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo-1}.html">&lt;</a></c:when>
						<c:otherwise><a class="prev dis" href="javascript:void('0');">&lt;</a></c:otherwise>
					</c:choose>
					<!-- 第一页 -->
					<c:choose>
						<c:when test="${searchDto.currPageNo == 1}"><span class="cur">1</span></c:when>
						<c:otherwise><a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_1.html">1</a></c:otherwise>
					</c:choose>
					<c:if test="${searchDto.currPageNo>=5}">
						<span>...</span>
					</c:if>
					
					<!-- mid0  --------------s-->
					<c:if test="${searchDto.currPageNo >= 3}">
						<c:choose>
							<c:when test="${searchDto.currPageNo == 3}">
								<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_2.html">2</a>
							</c:when>
							<c:when test="${searchDto.currPageNo > 3}">
								<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo - 2}.html">${searchDto.currPageNo - 2}</a>
								<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo - 1}.html">${searchDto.currPageNo - 1}</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:if>
					<!-- mid0  --------------e-->
					
					<c:if test="${searchDto.currPageNo > 1 && searchDto.currPageNo < pageList.totalPages}">
						<span class="cur">${searchDto.currPageNo}</span>
					</c:if>
					
					<!-- mid1  --------------s-->
					<c:choose>
						<c:when test="${searchDto.currPageNo < (pageList.totalPages - 2)}">
							<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo + 1}.html">${searchDto.currPageNo + 1}</a>
							<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo + 2}.html">${searchDto.currPageNo + 2}</a>
						</c:when>
						<c:when test="${searchDto.currPageNo == (pageList.totalPages - 2)}">
							<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo + 1}.html">${searchDto.currPageNo + 1}</a>
						</c:when>
					</c:choose>	
					<!-- mid1  --------------s-->
					
					<c:if test="${searchDto.currPageNo<=pageList.totalPages-4}">
						<span>...</span>
					</c:if>
					<c:if test="${pageList.totalPages != 1 }">
						<c:choose>
							<c:when test="${searchDto.currPageNo == pageList.totalPages}">
								<span class="cur">${pageList.totalPages }</span>
							</c:when>
							<c:otherwise>
								<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${pageList.totalPages }.html">${pageList.totalPages
									}</a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<!-- 下一页 -->
					<c:choose>
						<c:when test="${pageList.hasNextPage}">
							<a class="next"
								href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${searchDto.currPageNo+1}.html">&gt;</a>
						</c:when>
						<c:otherwise>
							<a class="next dis" href="javascript:void('0');">&gt;</a>
						</c:otherwise>
					</c:choose>

					<!-- 尾页 -->
					<c:choose>
						<c:when test="${searchDto.currPageNo == pageList.totalPages}">
							<a href="javascript:void('0');" class="last dis">尾页</a>
						</c:when>
						<c:otherwise>
							<a href="${rootPath}/list_${searchDto.searchType}_${searchDto.selectedCityId}_${pageList.totalPages }.html"
								class="last">尾页</a>
						</c:otherwise>
					</c:choose>
					<span class="all">共${pageList.totalPages}页</span>
					<span class="page-input"> <input type="text" id="page_num" maxlength="5">
						<a href="javascript:void(0);" id="go_page">GO</a>
					</span>
					<span class="go">页</span>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
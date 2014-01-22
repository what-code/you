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
						<c:when test="${searchDto.currPageNo == 1}">
							<a href="javascript:void('0');" class="first dis">首页</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:pageEvent('1');" class="first">首页</a>
						</c:otherwise>
					</c:choose>
					<!-- 上一页 -->
					<c:choose>
						<c:when test="${pageList.hasPrevPage}">
							<a class="prev"
								href="javascript:pageEvent('${searchDto.currPageNo-1}');">&lt;</a>
						</c:when>
						<c:otherwise>
							<a class="prev dis" href="javascript:void('0');">&lt;</a>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${searchDto.currPageNo == 1}">
							<span class="cur">1</span>
						</c:when>
						<c:otherwise>
							<a href="javascript:pageEvent('1');">1</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${searchDto.currPageNo>=5}">
						<span>...</span>
					</c:if>
					<c:forEach begin="2" end="${pageList.totalPages-1 }" var="i"
						step="1">
						<c:choose>
							<c:when
								test="${searchDto.currPageNo-2 <= i && searchDto.currPageNo > i}">
								<a href="javascript:pageEvent('${i}');">${i}</a>
							</c:when>
							<c:when test="${searchDto.currPageNo == i}">
								<span class="cur">${i}</span>
							</c:when>
							<c:when
								test="${searchDto.currPageNo+2 >= i && searchDto.currPageNo < i}">
								<a href="javascript:pageEvent('${i}');">${i}</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${searchDto.currPageNo<=pageList.totalPages-4}">
						<span>...</span>
					</c:if>
					<c:if test="${pageList.totalPages != 1 }">
						<c:choose>
							<c:when test="${searchDto.currPageNo == pageList.totalPages}">
								<span class="cur">${pageList.totalPages }</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:pageEvent('${pageList.totalPages }');">${pageList.totalPages
									}</a>
							</c:otherwise>
						</c:choose>
					</c:if>
					<!-- 下一页 -->
					<c:choose>
						<c:when test="${pageList.hasNextPage}">
							<a class="next"
								href="javascript:pageEvent('${searchDto.currPageNo+1}');">&gt;</a>
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
							<a href="javascript:pageEvent(${pageList.totalPages});"
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
<%-- <c:if
	test="${curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'}">
	<div  style="text-align:center;margin-top:-18px;margin-bottom:10px">
		<script type="text/javascript">
		/*帮5游列表页960*90图片*/
		var cpro_id = "u1422687";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
	</div>
</c:if> --%>
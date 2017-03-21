<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>
	WELCOME TO ICSSE 2017
	</title>
	
	<link href="/resources/css/show_img.css" rel="stylesheet" type="text/css"></link>
    <script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
    <script src="/resources/js/Common.js" type="text/javascript"></script>
    <script src="/resources/js/PagingContent.js" type="text/javascript"></script>
	<link rel="shortcut icon" type="image/png" href="http://icsse2017.hcmute.edu.vn/Resources/Imagephoto/logo.png">
	<link href="/resources/css/lightbox.css" rel="stylesheet" type="text/css">
    <script src="/resources/js/lightbox.min.js" type="text/javascript"></script>
    <link href="/resources/css/search_Article.css" rel="stylesheet">
    <script language="javascript" type="text/javascript">
        function SearchProcess(event) {
            if (event.keyCode == 13) {
                OnSearch(document.getElementById('txtSearchArticle').value);
                return false;
            }
            return true;
        }
        function OnSearch(query) {
            PSCPortal.Services.CMS.SearchAndPaging(query, currentPage, OnSearchSuccess, OnSearchFailed);
        }

        function OnSearchSuccess(results, context, methodName) {
            var position = results.indexOf('_');
            totalRecords = results.substring(0, position);
            results = results.substring(position + 1);
            LoadContentAndPaging(results);
        }
        function OnSearchFailed(results, context, methodName) {
        }

        function retitleUrl(str) {
            str = str.replace(/^\s+|\s+$/g, ''); // trim
            str = str.toLowerCase();
            // remove accents, swap ñ for n, etc
            var from = "àáảãạăằắẳẵặâầấẩẫậđèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ·/_,:;";
            var to = "aaaaaaaaaaaaaaaaadeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyy------";
            for (var i = 0, l = from.length ; i < l ; i++) {
                str = str.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
            }
            str = str.replace(/[^a-z0-9 -]/g, '') // remove invalid chars
              .replace(/\s+/g, '-') // collapse whitespace and replace by -
              .replace(/-+/g, '-'); // collapse dashes
            return str;
        };

        function popunder() {
            $('a').not('[href^="http"],[href^="https"],[href^="mailto:"],[href^="#"]').each(function () {
                $(this).attr('href', function (index, value) {
                    if (value != undefined) {
                        var index = value.indexOf("Default.aspx");
                        if (index >= 0)
                            return value = "/" + value.substring(index, value.length);
                    }
                });
            });
            var articleId = '' != '' ? '' : '';
            var topicId = '' != '' ? '' : '';
            var moduleId = '' != '' ? '' : '';
            if (articleId != '') {
                if (window.history.state == null) {
                    if (articleId.indexOf("/") > -1)
                        articleId = articleId.substr(0, articleId.indexOf("/"));
                    window.history.replaceState({ ArticleId: articleId }, "ArticleId", "/ArticleId/" + articleId + "/" + retitleUrl(''));
                }
            }
            if (topicId != '') {
                if (window.history.state == null) {
                    if (topicId.indexOf("/") > -1)
                        topicId = topicId.substr(0, topicId.indexOf("/"));
                    window.history.replaceState({ TopicId: topicId }, "TopicId", "/TopicId/" + topicId + "/" + retitleUrl(''));
                }
            }
            
        }
    </script>
    

<link href="/resources/img/WebResource.axd" type="text/css" rel="stylesheet" class="Telerik_stylesheet">
<link href="/resources/img/WebResource(1).axd" type="text/css" rel="stylesheet" class="Telerik_stylesheet">
<link href="/resources/img/WebResource(2).axd" type="text/css" rel="stylesheet" class="Telerik_stylesheet">
</head>
<div id="ctl06_portlet_800886a8-ccb5-40dd-813a-2c02ac0d2e92_RadMenu" class="RadMenu RadMenu_Green rmSized" style="height:40px;z-index: 1; width: 100%;">
						<ul class="rmRootGroup rmHorizontal">
							<li class="rmItem rmFirst"><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?PageId=8cb7e0c5-10f3-438b-8881-a74c24b373e3" class="rmLink rmRootLink"><span class="rmText">Home</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Portlets/MenuRad/#" class="rmLink rmRootLink"><span class="rmText rmExpandDown">About</span></a><div class="rmSlide">
								<ul class="rmVertical rmGroup rmLevel1">
									<li class="rmItem rmFirst"><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=f9111384-c9ad-46ac-ba10-037bf3d691a4" class="rmLink"><span class="rmText">icsse</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=c8018710-9562-44c6-bef0-2a0f22e73f6b" class="rmLink"><span class="rmText">HCMUTE</span></a></li><li class="rmItem rmLast"><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=dd21088d-cb55-4f9d-ac9c-73553c715cae" class="rmLink"><span class="rmText">Committees</span></a></li>
								</ul>
							</div></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=fc8c1223-a9d2-46b5-9db3-665c778f0ac9" class="rmLink rmRootLink"><span class="rmText">Call for papers</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=945864b5-165f-45a3-8e75-a9ba5ac1f9ef" class="rmLink rmRootLink"><span class="rmText">Submission</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=707e0e24-5220-4821-a40f-4041eb76cc1a" class="rmLink rmRootLink"><span class="rmText">Registration</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=be204435-0615-4fc3-aa86-392d871decc8" class="rmLink rmRootLink"><span class="rmText">Keynote Speakers</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Portlets/MenuRad/#" class="rmLink rmRootLink"><span class="rmText">Program</span></a></li><li class="rmItem "><a href="http://icsse2017.hcmute.edu.vn/Portlets/MenuRad/#" class="rmLink rmRootLink"><span class="rmText">Venue &amp; Hotel</span></a></li><li class="rmItem rmLast"><a href="http://icsse2017.hcmute.edu.vn/Default.aspx?ArticleId=b4031ebe-e802-4402-a49a-5769943872a5" class="rmLink rmRootLink"><span class="rmText">Contact</span></a></li>
						</ul><input id="ctl06_portlet_800886a8-ccb5-40dd-813a-2c02ac0d2e92_RadMenu_ClientState" name="ctl06_portlet_800886a8-ccb5-40dd-813a-2c02ac0d2e92_RadMenu_ClientState" type="hidden" autocomplete="off">
					</div>
</html>
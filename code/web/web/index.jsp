<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/27
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="container">
    <!-- test card -->
    <div class="card">
        <div class="card-block">
            <div class="row">
                <div class="col-md-12">
                    <p style="color: #D8D8D8"><small>来自标签 <strong>Spring</strong></small></p>
                    <div class="row">
                        <div class="col-md-2 text-center mx-auto">
                            <img src="image/cover/notebook_2.png" style="height: 100px; width: 100px;">
                        </div>
                        <div class="col-md-10">
                            <h4 class="card-title" style="margin-bottom: 6px;">Spring Security 笔记</h4>
                            <i class="fa fa-tag" aria-hidden="true"></i>
                            <kbd class="card-subtitle">Spring</kbd>&nbsp;<kbd class="card-subtitle">Spring Security</kbd>
                            <p class="card-text" style="word-break: break-all;">
                                Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements.
                            </p>
                            <footer>
                                <small>创建者 <strong>rudeigerc</strong> · 所有者 <strong>rudeigerc</strong> · 创建时间 2017-06-04 11:32:37</small>
                                <br><br>
                                <button class="btn btn-outline-secondary center-block" :class="star.selected? 'active' : ''" @click="star_select()" type="button" style="border: none;">
                                    <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;{{ star.star }}
                                </button>
                                <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                    <i class="fa fa-comments fa-fw" aria-hidden="true"></i>&nbsp;评论
                                </button>
                                <button class="btn btn-outline-secondary center-block btn-collection" :class="collection.selected? 'active' : ''" @click="collection_select()" type="button" style="border: none;">
                                    <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                </button>
                                <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                    <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                </button>
                            </footer>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript">
    vm = new Vue({
        el: '.card',
        data: {
            star: {
                star: 20,
                selected: false
            },
            collection: {
                selected: false
            }
        },
        methods: {
            star_select: function() {
                if (!this.star.selected) {
                    this.star.selected = true;
                    this.star.star++;
                }
                else {
                    this.star.selected = false;
                    this.star.star--;
                }
            },
            collection_select: function() { this.collection.selected = !this.collection.selected; }
        }
    })
</script>
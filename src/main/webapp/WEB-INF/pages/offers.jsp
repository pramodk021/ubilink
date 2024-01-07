<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<!--     <link href="resources/assets/css/bootstrap.css" rel="stylesheet" type="text/css" /> -->
    <!-- <script src="resources/assets/scripts/iosBridge.js"></script> -->
    <style>
        .EmptycolorIcon
        {
            position: absolute; top: 5px; left: 88%
			!important;
			border:none !important;
			height:30px;
			width:30px;
			background-image:url('resources/assets/images/star-1.png')
        }
           .colorIcon
        {
            position: absolute; top: 5px; left: 88%
			!important;
			height:30px;
			width:30px;
			background-image:url('resources/assets/images/star-2.png')
        }
    
    </style>
    <script>

        function onErrorCallingNativeFunction(err) {
            if (err) {
                alert(err.message);
            }
        }

        var datalimit = 10;
        var fvrt = 0;
        $(document).ready(function () {

            if (document.location.href.indexOf('#fvrts') > -1) {
                // $('#a0').animate({ "left": "250" }, "slow");
                fvrt = 1;
                var fvrts = getCookie("fvrts");

                var ArrayFvrts = fvrts.split(',');

                for (var i = 0; i <= datalimit; i++) {

                    if ($.inArray("#a" + i, ArrayFvrts) != -1) {
                        $("#a" + i).show("slow");
                        $("#a" + i).find(".EmptycolorIcon").removeClass("glyphicon-star-empty EmptycolorIcon").addClass("glyphicon-star colorIcon");
                    }
                    else {
                        $("#a" + i).hide("slow");
                    }

                }
            }
            else {

                calliOSFunction("getFav", [], function (ret) {
                    var listValues = JSON.parse(ret.result);

                    for (var i = 0; i <= datalimit; i++) {

                        if ($.inArray("#a" + i, listValues) != -1) {
                            $("#a" + i).show("slow");
                            $("#a" + i).find(".EmptycolorIcon").removeClass("glyphicon-star-empty EmptycolorIcon").addClass("glyphicon-star colorIcon");
                        }
                        else {
                            $("#a" + i).show("slow");
                        }

                    }

                }, onErrorCallingNativeFunction);
         
         
         
         
//                var fvrts = getCookie("fvrts");

//                var ArrayFvrts = fvrts.split(',');

//                for (var i = 0; i <= datalimit; i++) {

//                    if ($.inArray("#a" + i, ArrayFvrts) != -1) {
//                        //                        $("#a" + i).hide("fast");
//                        $("#a" + i).find(".EmptycolorIcon").removeClass("glyphicon-star-empty EmptycolorIcon").addClass("glyphicon-star colorIcon");
//                    }
//                    else {
//                        $("#a" + i).show("slow");
//                    }

//                }
            }


        });
        function setCookie(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toGMTString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
        }
        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i].trim();
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        }

        function AddToFvrt(id) {
           
		  // alert(id);

            if ($("#" + id).find(".EmptycolorIcon").length == 0) {
                $("#" + id).find(".colorIcon").removeClass("glyphicon-star colorIcon").addClass("glyphicon-star-empty EmptycolorIcon");

                calliOSFunction("addRemoveFvrt", ["#" + id], function (ret) {
                    alert(ret);
                }, onErrorCallingNativeFunction);

//                var fvrts = getCookie("fvrts");

//                var ArrayFvrts = fvrts.split(',');

//                ArrayFvrts.splice($.inArray("#" + id, ArrayFvrts), 1);

//                if (fvrt == 1) {
//                    $("#" + id).hide('slow');
//                }
//                        var fvrts = "";

//                    if (ArrayFvrts.length > 0)
//                    {
//                        for (var j = 0; j < ArrayFvrts.length; j++) {
//                            if (ArrayFvrts[j] != "undefined" && ArrayFvrts[j] != "undefined,") {
//                                fvrts = fvrts + ArrayFvrts[j] + ",";
//                            } 
//                        }
//                    }

//                    setCookie("fvrts", fvrts, 30);
            }
            else {

                $("#" + id).find(".EmptycolorIcon").removeClass("EmptycolorIcon").addClass("colorIcon");

                calliOSFunction("addRemoveFvrt", ["#" + id], function (ret) {
                  
                }, onErrorCallingNativeFunction);

//                var fvrts = getCookie("fvrts");
//                fvrts = fvrts + "," + "#" + id;

//                setCookie("fvrts", fvrts, 30);
            }

        }

    </script>
</head>
<body>
<a href="/ubilink/Offer/ViewOffer.htm">View Offer</a><br/>
    <div class="bs-example">
        <div class="list-group">
      
                <a href="javascript:void(0)" id="a0" class="list-group-item" style="padding: 0px 5px;">
                    <div style="position: relative; left: 0; top: 0;">
                        <img class="img-responsive" src="../../resources/assets/images/image-ne11w.png" style="position: relative;
                        top: 0; left: 0;" />
                    <img class="EmptycolorIcon" onclick="AddToFvrt('a0');"
                            >
                        </img>
                    </div>
                </a><a href="javascript:void(0)" id="a1" class="list-group-item" style="padding: 0px 5px;">
                    <div style="position: relative; left: 0; top: 0;">
                        <img class="img-responsive" src="E:/WonderBiz Server Projects/ubilink/src/main/webapp/WEB-INF/pages/resources/assets/images/image-new-2.png" style="position: relative;
                        top: 0; left: 0;" />
                        <img class="EmptycolorIcon" onclick="AddToFvrt('a1');"
                            >
                        </img>
                        </div
                    ></a><a href="javascript:void(0)" id="a2" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-3.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a2');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a3" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-4.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a3');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a4" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-5.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a4');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a5" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-6.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a5');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a6" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-7.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a6');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a7" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-8.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a7');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a8" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-9.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a8');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a9" class="list-group-item" style="padding: 0px 5px;">
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-10.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a9');"
                                >
                            </img>
                        </div>
                    </a>
                    <a href="javascript:void(0)" id="a10" class="list-group-item" style="padding: 0px 5px;" >
                        
                        <div style="position: relative; left: 0; top: 0;">
                            <img class="img-responsive" src="resources/assets/images/image-new-11.png" style="position: relative;
                            top: 0; left: 0;" />
                            <img class="EmptycolorIcon" onclick="AddToFvrt('a10');"
                                >
                            </img>
                        </div>
                    </a>
        </div>
        <!--<button type="button" onclick="AddBtnClick();">
            Add</button>
        <button type="button" onclick="RemoveBtnClick();">
            Remove</button>-->
    </div>
</body>
</html>

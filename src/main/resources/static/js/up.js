$(function() {

    var delParent;
    var defaults = {
        fileType: ["jpg", "png", "bmp", "jpeg"],
        // 上传图片支持的格式
        fileSize: 1024 * 1024 * 10 // 上传的图片大小不得超过 10M
    };
    /*点击图片*/
    $(".file").change(function() {
        var idFile = $(this).attr("id");

        var file = document.getElementById(idFile);
        // 获取父元素
        var imgContainer = $(this).parents(".aui-photo");
        var img = imgContainer.prevObject;
        //存放图片的父元素
        var fileList = file.files;
        //获取的图片文件
        console.log(fileList + "======filelist=====");
        var input = $(this).parent();
        //文本框的父亲元素
        var imgArr = [];
        //遍历得到的图片
        var numUp = imgContainer.find(".aui-up-section").length;
        var totalNum = numUp + fileList.length;
        //图片总的数量可自定义
        if (fileList.length > 3 || totalNum > 3) {
            alert("你好！上传图片不得超过3张，请重新选择");
            //一次选择上传超过3个  自己定义
        } else if (numUp < 3) {
            fileList = validateUp(fileList , img);
            for (var i = 0; i < fileList.length; i++) {
                var imgUrl = window.URL.createObjectURL(fileList[i]);
                imgArr.push(imgUrl);
                var $section = $("<section class='aui-up-section fl loading' id='imgId'>");
                imgContainer.prepend($section);
                var $span = $("<span class='aui-up-span'>");
                $span.appendTo($section);

                var $img0 = $("<img class='aui-close-up-img'>").on("click", function(event) {
                    event.preventDefault();
                    event.stopPropagation();
                    $(".aui-works-mask").show();
                    delParent = $(this).parent();
                });
                $img0.attr("src", "images/close.jpg").appendTo($section);
                var $img = $("<img class='aui-to-up-img aui-up-clarity'>");
                $img.attr("src", imgArr[i]);
                $img.appendTo($section);
                var $p = $("<p class='img-aui-img-name-p'>");
                $p.html(fileList[i].name).appendTo($section);
                var $input = $("<input id='actionId' name='actionId' value='' type='hidden'>");
                $input.appendTo($section);
                var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                $input2.appendTo($section);

            }
        }
        setTimeout(function() {
            $(".aui-up-section").removeClass("loading");
            $(".aui-to-up-img").removeClass("aui-up-clarity");
        }, 4100);
        numUp = imgContainer.find(".aui-up-section").length;
        if (numUp >= 3) {
            $(this).parent().hide();
        }

        $(this).val("");
    });


    $(".aui-accept-ok").click(function() {

        var s = $(".aui-accept-ok");
        $(".aui-works-mask").hide();

        var numUp = delParent.siblings().length;
        if (numUp < 3) {
            delParent.parent().find(".aui-file-up").show();
        }
        // 获取到当前父标签下的p标签的值,和照片的值
        var key = delParent.find("p");
        var keyName = key.text();

        // 清除缓存
        cache.delete(keyName);
        delParent.remove();
    });

    $(".aui-accept-no").click(function() {
        $(".aui-works-mask").hide();
    });

    // 退出
    $(".btr").click(function () {
        $("#imgId").remove();
        cache.clear();
        $(".ccccc").hide();
    });

    // 提交
    $("#submit").click(function () {
        submitForm();
    });

    // 添加图片
    $("#submitFormBt").click(function () {
        $(".ccccc").toggle();
    })

    // 图片缓存
    var cache = new Map();


    function validateUp(files, img) {

        var arr = new Array();
        //替换的文件数组
        for (var i = 0, file; file = files[i]; i++) {
            //获取图片上传的后缀名
            var newStr = file.name.split("").reverse().join("");
            if (newStr.split(".")[0] != null) {
                var type = newStr.split(".")[0].split("").reverse().join("");
                console.log(type + "===type===");
                if (jQuery.inArray(type, defaults.fileType) > -1) {
                    // 符合图片格式，可以上传
                    if (file.size >= defaults.fileSize) {
                        alert(file.size);
                        alert('您这个"' + file.name + '"文件大小过大');
                    } else {
                        // 添加到缓存中并存储起来
                        cache.set(file.name, img[0].files[0]);
                        arr.push(file);
                    }
                } else {
                    alert('您这个"' + file.name + '"上传类型不符合');
                }
            } else {
                alert('您这个"' + file.name + '"没有类型, 无法识别');
            }
        }
        return arr;
    }

    function submitForm() {

        // 判断缓存中的图片数量
        if (cache.size <= 0) {
            alert("您没有添加图片")
        }

        // 有值添加图片
        else {
            var listImg = new Array();
            //将需要提交的参数封装起来
            var formData = new FormData();

            // 循环遍历缓存，并添加到formDate中
            cache.forEach(function (value, key) {
                console.log(key, value);
                listImg.push(value);
                //有file提交的时候走的接口
                formData.append("file", value);
            });


            $.ajax({
                url : '/editMovieInfo',
                type : 'post',
                data : formData,
                processData : false,
                contentType : false,
                success : function(value) {
                    var result = JSON.parse(value);
                    if (result.state > 0) {
                        var s = '';
                        for (var i = 0; i < result.webImgUrls.length; i++) {
                            s += result.webImgUrls[i] + '\n' ;
                        }

                        // 先清除再添加
                        $(".reMessage").empty()
                        $(".reMessage").append(s);

                        debugger
                        var s = $("#imgId");
                        while (s.length > 0) {
                            s = $("#imgId");
                            console.log(s.length);
                            // 清除缓存以及相关的处理
                            $("#imgId").remove();
                        }
                        cache.clear();
                        delParent = null;
                    } else {
                       alert("图片保存失败")
                    }
                }
            });


        }


    }
//图片回显:
//     function preview(file) {
//         $("#imgHidden").css("display", "none");
//         var prevDiv = document.getElementById('preview');
//         if (file.files && file.files[0]) {
//             var reader = new FileReader();
//             reader.onload = function(evt) {
//                 prevDiv.innerHTML = '<img style="width: 100px;height: 100px;" src="' + evt.target.result + '" />';
//             }
//             reader.readAsDataURL(file.files[0]);
//         } else {
//             prevDiv.innerHTML = '<div class="img" style="width: 100px;height:100px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' +
//                 file.value + '\'"></div>';
//         }
//     }

})



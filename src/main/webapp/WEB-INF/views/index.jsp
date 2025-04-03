<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>GUCI</title>

<jsp:include page="include/css.jsp" />
    
  </head>
  <body> 


<jsp:include page="include/header.jsp" />
 
<jsp:include page="include/menu.jsp" /> 
 

    <!-- Start slider -->
  <section id="aa-slider">
    <div class="aa-slider-area">
      <div id="sequence" class="seq">
        <div class="seq-screen" >
          <ul class="seq-canvas">
            <!-- single slide item -->
            <li>
              <div class="seq-model">
                <img data-seq src="/resources/img/cloth.png" alt="Men slide img" />
              </div>
            </li>
            <!-- single slide item -->
            <li>
              <div class="seq-model">
                <img data-seq src="/resources/img/cloth.png" alt="Wristwatch slide img" />
              </div>
            </li>
                 
          </ul>
        </div>
        <!-- slider navigation btn -->
        <fieldset class="seq-nav" aria-controls="sequence" aria-label="Slider buttons">
          <a type="button" class="seq-prev" aria-label="Previous"><span class="fa fa-angle-left"></span></a>
          <a type="button" class="seq-next" aria-label="Next"><span class="fa fa-angle-right"></span></a>
        </fieldset>
      </div>
    </div>
  </section>
  <!-- / slider -->

  <!-- Latest Blog -->
  <section id="aa-latest-blog">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-latest-blog-area">
            <h2>ベストレビュー</h2>
            <div class="row">
              <!-- single latest blog -->
              <div class="col-md-4 col-sm-4">
                <div class="aa-latest-blog-single">
                  <figure class="aa-blog-img">                    
                    <a href="/review/product_detail?gdsNo=6"><img src="/resources/admin/img/2021/01/25/a3147d5c-376d-4484-bbef-5f9ea7bb5129_paddingreview.png" alt="img"></a>                           
                  </figure>
                  <div class="aa-blog-info"><br>
                    <p>패딩이 가볍고, 보기보다 엄청 따듯해요!! 올겨울은 이 패딩 하나로 추위걱정이 없이 지낼수 있겠어요</p> 
                     <a href="/review/product_detail?gdsNo=6" class="aa-read-mor-btn">商品を見に行く <span class="fa fa-long-arrow-right"></span></a>         
                  </div>
                </div>
              </div>
              <!-- single latest blog -->
              <div class="col-md-4 col-sm-4">
                <div class="aa-latest-blog-single">
                  <figure class="aa-blog-img">                    
                    <a href="/review/product_detail?gdsNo=2"><img src="/resources/admin/img/2021/01/25/4ac8e6e4-1668-4c7d-be68-d95229430880_skirt.png" alt="img"></a>                           
                  </figure>
                  <div class="aa-blog-info"><br>
                    <p>nice pit!! Wonderful I want Rebuy</p> 
                     <a href="/review/product_detail?gdsNo=2" class="aa-read-mor-btn">商品を見に行く <span class="fa fa-long-arrow-right"></span></a>         
                  </div>
                </div>
              </div>
              <!-- single latest blog -->
              <div class="col-md-4 col-sm-4">
                <div class="aa-latest-blog-single">
                  <figure class="aa-blog-img">                    
                    <a href="/review/product_detail?gdsNo=1"><img src="/resources/admin/img/2021/01/25/e36f3ecf-53d5-4210-8aa3-ee88c7647b86_blueJeans2.png" alt="img"></a>                           
                  </figure>
                  <div class="aa-blog-info"><br>
                    <p>心地がいいジーンズでした。お勧めです</p> 
                     <a href="/review/product_detail?gdsNo=1" class="aa-read-mor-btn">商品を見に行く <span class="fa fa-long-arrow-right"></span></a>         
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- / Latest Blog -->
  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/resources/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- To Slider JS -->
  <script src="/resources/js/sequence.js"></script>
  <script src="/resources/js/sequence-theme.modern-slide-in.js"></script>  
  <!-- Product view slider -->
  <script type="text/javascript" src="/resources/js/jquery.simpleGallery.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.simpleLens.js"></script>
  <!-- slick slider -->
  <script type="text/javascript" src="/resources/js/slick.js"></script>
  <!-- Price picker slider -->
  <script type="text/javascript" src="/resources/js/nouislider.js"></script>
  <!-- Custom js -->
  <script src="/resources/js/custom.js"></script>  
  


    <jsp:include page="include/footer.jsp" />
  </body>
</html>
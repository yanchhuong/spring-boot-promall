<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
<head>
  <meta charset="UTF-8">
  <title>Auto History Search</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
  <html ng-app="Wikipedia">
<head>
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" type="text/css" />
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.css">
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
 <script type="text/javascript">
 $(document).ready(function(){
	  
 });	 
	
</script>

<style scoped="">
.carousel-control-prev-icon, .carousel-control-next-icon {
    height: 100px;
    width: 100px;
    outline: black;
    background-color: rgba(545, 0, 0, 0.3);
    background-size: 100%, 100%;
    border-radius: 50%;
    border: 1px solid black;
 }
 

    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img { margin : auto; }
    .linkout { background : #0072bc none repeat scroll 0 0; border-radius : 10px 0 0; height : 35px; line-height : 35px; margin-right : 0; padding : 0 20px 0 20px; text-decoration : none; position : absolute; bottom : -1px; left : 285px; width : 51px; color : #ffffff; cursor : zoom-in; }
    .carousel-control.right { background-image : none; margin-bottom : 13%; margin-top: 110px; font-size: xx-large; font-weight: bold; }
    .carousel-control.left { background-image : none; margin-bottom : 13%; margin-top: 110px; font-size: xx-large; font-weight: bold; }
    .highslide-caption { width : 100%; text-align : center; }
    .highslide-close { display : none !important; }
    .highslide-number { display : inline; padding-right : 1em; color : white; }
    .highslide-wrapper { position : relative !important; }
    @media (max-width : 414px) {
        .linkout { margin : 0 0 0 45px; }
    }
    @media (max-width : 375px) {
        .linkout { margin : 0 0 0 -6px; }
    }
    @media (max-width : 320px) {
        .linkout { margin : 0 0 0 -18px; }
    }
</style>

</head>
<body ng-controller="MainCtrl">

  <div class="jumbotron text-center">
    <h1>Auto History Search</h1>
    <h2>What is VIN ? <a href="https://twitter.com/intent/tweet?url=http%3A%2F%2Fbit.ly%2F1TcTW2T&via=freecodecamp&text=Check%20out%20the%20first%20Wikipedia%20voice%20search&hashtags=wikipedia&" target="_blank">
      <i class="fa fa-twitter-square fa-lg tweet"></i></a></h2>

    <div class="text-center ">
 
      <form class="form-inline" ng-submit="MainData.search(MainData.val)">
       <div class="form-group has-feedback">
         
          <input type='text' class="form-control dirty ui-autocomplete-input" autocomplete="off" placeholder='Search' ng-model="MainData.val" required/>
            <!--  <span class='mic_span'><a class="fa fa-microphone fa-lg form-control-feedback icon" style="pointer-events: auto;" ng-click="MainData.Speech()"></a>
            </span> -->
        </div>
        
        <button type="button" class="btn btn-info autoplay" ng-click="MainData.Random()">Search</button>
     </form>
    </div>

  </div>
  
  <div class="container" >
   <div style="padding-bottom:1px;" class="white_bkg btop0_1611 bbot0_1611">
        <div class="row commomn_top_sec">
              <div class="row">
                  <div class="col-md-8">
                         <div class="">
                              <div class="col-lg-6 col-sm-6">
                                    <h3>Vehicle Identification Number:</h3>
                                    <div class="vin_box">1FT7X2B68CEA05672</div>
                              </div>
                              <div class="col-lg-6 col-sm-6">
                                     <h3>Report Date:</h3>
                                     <div class="vin_box">November 10, 2017</div>
                              </div>
                       </div>
                 </div>
                 <div class="col-md-4">
                        <div class="col-lg-12 col-sm-12 text-right">
							<div class="highslide-gallery r-highslide-gallery">
							
							<div id="myCarousel" class="carousel slide" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
								  <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
								  <li data-target="#myCarousel" data-slide-to="1"></li>
								  <li data-target="#myCarousel" data-slide-to="2"></li>
								</ol>
								
								<!-- Wrapper for slides -->
								<div class="carousel-inner max-width-400" role="listbox">
								
								</div>
								
								<!-- Left and right controls -->
								  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
								    <span class="glyphicon glyphicon-chevron-left"></span>
								    <span class="sr-only">Previous</span>
								  </a>
								  <a class="right carousel-control" href="#myCarousel" data-slide="next">
								    <span class="glyphicon glyphicon-chevron-right"></span>
								    <span class="sr-only">Next</span>
								  </a>
								</div>
							</div>
                      </div>
               </div>
          </div>
     </div>
   </div>
    
  <!--List Data  -->
   	<div class="row">
        	<div class="col-lg-12 no_pad_mob">
            	<div class="grey_bkg grey_bkg_1611">
                	<div class="white_bkg btop0_1611">
                    	<br>
                    	<div class="top_headind2_1611">
                        	<h1>                                                                                                                </h1>
                    	</div>
						<div class="row" style="padding-left: 16px;">
    			 		<div class="pull-left">
         					<img class="toyota" src="https://s3-us-west-1.amazonaws.com/vehicle-history/images/car-makes/ford.jpg">
     					</div>
    					<div class="pull-left">
        					<h1 style="margin: 0; padding: 10px 20px;">2012 Ford F 250 SD<br><span>1FT7X2B68CEA05672</span></h1>
     					</div>
					</div>
   				    <br>
   				     <div class="hidden-xs">
    					<table class="table table-bordered">
        					<tbody>
           					  <tr><td width="30%">VIN<br> <strong>1FT7X2B68CEA05672</strong></td>
            					<td>Make<br> <strong>Ford</strong></td>
            					<td>Model<br> <strong>F-250 SD</strong></td>
            					<td>Trim<br> <strong>F-250 SD</strong></td>
            					<td>Year<br> <strong>Lariat SuperCab 4WD</strong></td>
            					<td>Vehicle Age<br> <strong>5 year(s)</strong></td>
        					</tr> 
        				</tbody>
   					 </table>
					</div>
					<div class="container" style="margin-left: -15px;">
    				      <div class="hidden-xs">
        					   <table class="table table-bordered">
            					   <tbody><tr>
                							<td width="25%">VIN</td>
                							<td><strong>1FT7X2B68CEA05672</strong></td>
            							</tr>
            							<tr>
                							<td width="25%">Make</td>
                							<td><strong>Ford</strong></td>
            							</tr>
            							<tr>
                							<td width="25%">Model</td>
                							<td><strong>F-250 SD</strong></td>
            							</tr>
            							<tr>
                							<td width="25%">Year</td>
                							<td><strong>2012</strong></td>
           							    </tr>
            							<tr>
                							 <td width="25%">Trim</td>
               								 <td><strong>Lariat SuperCab 4WD</strong></td>
            							</tr>
            							 <tr>
                						 	<td width="25%">Vehicle Age</td>
                						 	<td><strong>5 year(s)</strong></td>
           								 </tr>
        					        </tbody>
        					</table>
   					    </div>
					</div>
                    <br>
 				
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    
    <div ng-repeat="term in MainData.results">
      <md-content class="md-padding ">
        <md-card>
        <md-card-content class="card">
        <h2 class="md-title">{{term.title}} <a ng-href="{{term.url}}" target="_blank"><i class="fa fa-external-link-square"></i></a></h2>
        <p>{{term.text}}</p>
      </md-card-content>
    </div>
  </div>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js">
  </script>
  <!-- Angular Material  -->
  <script src="https://ajax.googleapis.com/ajax/libs/angular_material/0.8.3/angular-material.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/annyang/1.6.0/annyang.min.js"></script>
 
</body>
</html>
    <script src="js/auto_vehicle.js"></script>

</body>
</html>

function showResults(housesStr) {
    var houses = housesStr.slice(0, 12);
    var htmlStr = "";
    
    houses.forEach(function(house) {
    	house.score = parseFloat(Math.round(house.score * 100) / 100).toFixed(2);
    	switch (house.bus) {
    	case 0:
    	case 1: 
    		house.bus = "rất xa !"; break;
    	case 2: house.bus = "xa"; break;
    	case 3: house.bus = "trung bình"; break;
    	case 4: house.bus = "gần"; break;
    	case 5: house.bus = "rất gần !"; break;
    	}
     	
    	if(house.isWithHost == 0) {
    		house.isWithHost = "không ở cùng";
    	} else {
    		house.isWithHost = "ở cùng";
    	}
    });
    
    houses.forEach(function(house) {
        htmlStr +=
        `
        <div class="item col-lg-3 col-md-6 col-xs-12 landscapes" style="margin-top: 10px">
          <div class="project-single">
            <div class="project-inner project-head">
              <div class="project-bottom">
                <h4><a href="properties-details.html">View Property</a><span class="category">Real Estate</span></h4>
              </div>
              <div class="button-effect">
                <a href="properties-details.html" class="btn"><i class="fa fa-link"></i></a>
                <a href="https://www.youtube.com/watch?v=2xHQqYRcrx4" class="btn popup-video popup-youtube"><i class="fas fa-video"></i></a>
                <a class="img-poppu btn" href="images/feature-properties/fp-1.jpg" data-rel="lightcase:myCollection:slideshow"><i class="fa fa-photo"></i></a>
              </div>
              <div class="homes">
                <!-- homes img -->
                <a href="properties-details.html" class="homes-img">
                 <!--  <div class="homes-tag button alt featured">Featured</div>
                  <div class="homes-tag button alt sale">For Sale</div>
                  <div class="homes-price">Family Home</div> -->
                  <img src="images/feature-properties/fp-1.jpg" alt="home-1" class="img-responsive">
                </a>
              </div>
            </div>
            <!-- homes content -->
            <div class="homes-content">
                <table class="table table-sm">
                    <tbody>
                        <tr>
                            <th>Điểm số</th>
                            <td>${house.score}</td>           
                        </tr>
                        <tr>
                            <th>Giá</th>
                            <td>${house.price}</td>
                            <td>(VNĐ)</td>
                        </tr>
                        <tr>
                            <th>Khoảng cách</th>
                            <td>${house.distance}</td>
                            <td>(Km)</td>
                        </tr>
                        <tr>
                            <th>Diện tích</th>
                            <td>${house.acreage}</td>
                            <td>(m2)</td>
                        </tr>
                        <tr>
                            <th>Chu kỳ</th>
                            <td>${house.term}</td>
                            <td>(tháng)</td>
                        </tr>
                        <tr>
                            <th>Xe bus</th>
                            <td>${house.bus}</td>
                        </tr>
                        <tr>
                            <th>Chủ</th>
                            <td>${house.isWithHost}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
          </div>
        </div>
        `
    });
    
    $("#result-space").empty();
    $("#result-space").append(htmlStr);
    $("#tail").remove();
}

function scroll_func() {
	console.log("oke scrolle")
	$('html,body').animate({
        scrollTop: $("#search-area-aaa").offset().top},
        'slow');
}

function submit_search() {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/hello",
		data : JSON.stringify({
			distance : parseFloat($("#distance").val()),
			acreage : parseFloat($("#acreage").val()),
			term : parseFloat($("#term").val()),
			price : parseFloat($("#price").val()),
			bus : parseInt($("#bus").val()),
			isWithHost : parseInt($("#host").val()),
			distance_score : parseFloat($("#distance-score").val()),
			acreage_score : parseFloat($("#acreage-score").val()),
			term_score : parseFloat($("#term-score").val()),
			price_score : parseFloat($("#price-score").val()),
			bus_score : parseFloat($("#bus-score").val()),
			isWithHost_score : parseFloat($("#host-score").val())
		}),
		dataType : 'json',
		timeout : 100000,
		success: function(data) {
			  showResults(data);
		  }
		});
}



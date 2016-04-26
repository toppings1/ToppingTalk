var fs = require('fs');
var connect = require('connect');
var express=require('express');
var app=express();
var bodyParser = require('body-parser')
app.use(express.static(__dirname + '/public'));

//app.use(app.router); 있으면 업로드가 안돼

app.set("jsonp callback", true);

app.use(bodyParser.urlencoded({
    extended: true
}));

var logSockets = new Array();
var joinInfoList = new Array();
var joinList = new Array();
var messageList = new Array();
var logging=[];
var logout=[];
var clientChatRoomInfo='';

//joinList.push(createJoin(item, roomNo));

//안씀요
app.get('/',function(request,response,next){
	var requestString=JSON.stringify(request.query);
	var obj=JSON.parse(requestString);
	clientId=obj.id;
	
   fs.readFile('main.html', function (error, data) {
        response.writeHead(200, { 'content-type': 'text/html' });
        response.end(data);
    });
});

//첫번쨰 접속
app.get('/chatRoomList',function(request,response,next){
	
	var requestString=JSON.stringify(request.query);
	var obj=JSON.parse(requestString);
	var clientId=obj.id;
	var roomList=synchChatRoomList(clientId);
	var jsonpCallback = request.query.jsonp;
	
	listJson=JSON.stringify(roomList);
	console.log("#####"+roomList);
	console.log("^^^^"+listJson);
	var log=createLogInfo(clientId,new Date().format('yyyy.MM.dd/hh:mm:ss'));
		
	logging.push(log);
	
	//moveLogout(clientId);
	setLogInfoToDb(logout);
	console.log("회원의 접속누적회수: "+getClientLoginCount(clientId));
	console.log("일정기간동안의 로그기록 : "+getLogCntByDb("2014.01.24","2014.01.25"));
	
	console.log(logging);
	console.log(logout);
	
	response.jsonp(listJson);
	
});



app.get('/room',function(request,response,next){
/*	console.log("하이");
	
//	console.log(request);
	console.log(request.query);
	
	var clientId=request.query.clientId;
	var roomNo=request.query.roomNo;
	var mineFlag=request.query.mineFlag;
	
	clientChatRoomInfo={clientId:clientId,roomNo:roomNo,mineFlag:mineFlag};
	
	*/
   fs.readFile('chatRoom.html', function (error, data) {
	//    var requestString=JSON.stringify(request.query);
	//	var obj=JSON.parse(requestString);
        response.writeHead(200, { 'content-type': 'text/html' });
        response.end(data);
    });
   
});

app.get('/inviteList',function(request,response,next){
	var requestString=JSON.stringify(request.query);
	var obj=JSON.parse(requestString);
	clientId=obj.id;
	
	fs.readFile('inviteList.html', function (error, data) {
		response.writeHead(200, { 'content-type': 'text/html' });
	    response.end(data);
	});
});

//음성 요청 들어왔을때
app.get('/recorder',function(request,response,next){
	   fs.readFile('recorder.html', function (error, data) {
	        response.writeHead(200, { 'Content-type': 'text/html' });
	        response.end(data, function(error){
	        	console.log(error);
	        });
	    });
});

//파일전송 요청 들어왔을때
app.get('/fileTransfer',function(request,response,next){
	   fs.readFile('selectFile.html', function (error, data) {
	        response.writeHead(200, { 'Content-type': 'text/html' });
	        response.end(data, function(error){
	        	console.log(error);
	        });
	    });
});

//대화방에서 상대 프로필 사진 눌렀을 때
app.get('/profile',function(request,response,next){
	fs.readFile('profile.html', function (error, data) {
		response.writeHead(200, { 'content-type': 'text/html' });
	    response.end(data);
	});
});

var filePath;
var aFilePath;
// 회원이 선택한 파일을 서버 컴퓨터에 저장
app.post('/upload', function(request, response){
	console.log('request');
	console.log(request);
	console.log('request end...');
	
	fs.readFile(request.files.uploadFile.path, function(error, data){
		console.log("!");
		var tempPath1 = __dirname.replace(/\\/gi, "/");
		console.log(tempPath1);
		var tempPath = tempPath1 + "/files/" + request.files.uploadFile.name;
		console.log('tempPath start');
		console.log(tempPath);
		filePath = __dirname + "\\files\\" + request.files.uploadFile.name;
		console.log('filePath start');
		console.log(filePath);
		console.log('__dirName start');
		console.log(__dirname);
//		aFilePath = filePath;
//		aFilePath = "<a href=\"192.168.0.18\\" + filePath + "\">" + request.files.uploadFile.name + "</a>";
		
//		aFilePath = "<a href=\"#\"" + request.files.uploadFile.name + "\" download=\"" + request.files.uploadFile.name + "\">" + request.files.uploadFile.name + "</a>";

		console.log(request.files.uploadFile.name);
		aFilePath = request.files.uploadFile.name;
//		<a href="./files/b.mp3" download="b.mp3">download</a>
//		aFilePath = '<a href="./' + request.files.uploadFile.name + '" download="' + request.files.uploadFile.name + '">' + request.files.uploadFile.name + '</a>';
//		aFilePath = '<a href="http://192.168.0.18:52273/' + tempPath + '" download="' + request.files.uploadFile.name + '">' + request.files.uploadFile.name + '</a>';
//		aFilePath = '<a href="files:///' + filePath + '" download="' + request.files.uploadFile.name + '">' + request.files.uploadFile.name + '</a>';
		console.log('aFilePath start');
		console.log(aFilePath);
		fs.writeFile(tempPath, data, function(error){
			if(error){
				console.log(filePath);
				throw err;
			}
			else{
				response.redirect("back");
			}
		});
		console.log("gay");
		
		// 서버에 저장한 파일 url을 메시지로 전송
		
		console.log('start');
		var clientId = request.body.clientId;
		console.log(clientId + "!");
		var date = request.body.date;
		console.log(date + "@");
		var key = request.body.key;
		console.log(key + "#");
		var roomNumber = request.body.roomNo;
		console.log(roomNumber + "$");
		var count = countRead(roomNumber);
		
    	var list = searchJoinInfoListByRoomNo(roomNumber);
    	if(list != null){
    		list.forEach(function(item, index){
    			item.socket.emit('fileDown', createFileMessage(clientId, date, key), count);
    		});
    	}
	});
});

// 파일 내려주는
app.get('/download/:id', function(req, res){
	var filename = req.params.id;
	filepath = __dirname + "/files/" + filename;
	res.download(filepath);
});

var server = app.listen(52273, function(){
	   console.log('server running at http://192.168.0.10:52273');
	});

var socket = require('socket.io');

var io = socket.listen(server);


//var db = require('mongojs').connect('node',['history','logging',]);
var db = require('mongojs').connect('testdb',['history','logging','userTable','friendTable']);




Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p|ms)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 24) ? h : 24).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            case "ms": return d.getMilliseconds();
            default: return $1;
        }
    });
};
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

console.log(new Date().format('yyyy.MM.dd/hh:mm:ss'));
setInterval("setLogInfoToDb()", 1000*60*60*24);

io.set('log level', 2);
io.sockets.on('connection', function (socket) { //소켓과 연결 후
	
	//특정한 방에 접속하게 만드는 이벤트
	socket.on('join', function (data){
		var clientId = data.clientId;
		var roomNo = data.roomNo;
		
		if(!isJoinInfo(clientId, roomNo)){
    		socket.name = clientId;
    		socket.rooms = roomNo;
	    	socket.join(roomNo);
	    	joinInfoList.push(createJoinInfo(socket));
			
	    	synchList(roomNo);
    	}else{
    		socket.emit('alreadyJoined');
    		//searchJoinInfoListTarget(data.name, data.roomNo).emit('focusOn');
    	}
    });
	   
	    //로그인시 대화방 목록 뿌려주기
	socket.on('log', function(data){
    	if(!isLog(data.name)){
			socket.name = data.clientId;
			socket.rooms = data.roomNo;
			logSockets.push(socket);
			socket.join(data.roomNo);
			socket.emit('chatRoomList', synchChatRoomList(data.clientId));
			/**/console.log("*******"+data.clientId);
		}else{/*이미 로그인 되어 있는 상태*/}
    });
	
	  //같은 방에 속한 클라이언트에게 메시지를 전달하는 이벤트
	socket.on('message', function (data) {
    	if(!isReject(data.clientId, socket.rooms) && isJoined(data.clientId,socket.rooms)){
	    	var room = socket.rooms;
	    	var count = countRead(room);
	    	joinInfoList.forEach(function(item, index){
				if(item.socket.rooms == room && item.rejection != 'reject'){
					item.socket.emit('message',data, count);
				}
			});
	    	var target = searchMessageListElement(room);
	    	if(target != null){
	    		target.msgList.push(createMsgInfo(data, count));
	    	}
    	}
    });
	    
	    //초대
	socket.on('invite', function(roomNo, list){
    	list.forEach(function(item, index){
    		if(!isJoined(item, roomNo)){
    			joinList.push(createJoin(item, roomNo));
    			
    			joinInfoList.forEach(function(sitem, index){
    				if(sitem.socket.rooms == roomNo){
    					sitem.socket.emit('chatRoomIn', item);
    				}
    			});
	    	}
    	});
    	
    	if(!isCreatedRoom(roomNo)){
    		messageList.push(createMsgListElement(roomNo));
    	}
    	
    	list.forEach(function(item, index){
    		sendChatRoomList(item, synchChatRoomList(item));
    		var target = searchLogSocket(item);
    		if(target != null){
    			target.emit('requestInvite', {roomNo:roomNo,clientId:item});
    		}
    	});
    });

	    //대화방에서 나가지 않고 소켓연결이 끊어졌을 때
	socket.on('disconnect', function(){
		console.log(socket.name, socket.rooms);
    	deleteJoinInfo(socket.name, socket.rooms);
    	saveLastReadDate(socket.name, socket.rooms, new Date().format('yyyy.MM.dd/hh:mm:ss/ms'));
    	synchList(socket.rooms);
    });
   //수신거부 상태 요청
    socket.on('rejection', function(data){
    	if(isMultiChatRoom(data.roomNo)){
    		reject(data.clientId, data.roomNo);
    	}
    });
    //로그아웃 요청 이벤트
    socket.on('logout', function(clientId){
    	console.log("로그아웃?"+clientId);
    	if(isLog(clientId)){
    		moveLogout(clientId);	//로그아웃 할 때, 로그인 한 client를 logout한 로그기록 배열로 이동
    		deleteLogSocket(clientId);
    	}
    });
    
    //대화방 나가기 이벤트
    socket.on('outChatRoom', function(data){
        if(isJoined(socket.name, socket.rooms)){
           outChatRoom(data.clientId, data.roomNo);
           sendChatRoomList(data.clientId, synchChatRoomList(data.clientId));
           if(searchJoinListByRoomNo(data.roomNo) != null){
              joinInfoList.forEach(function(item, index){
                 if(item.socket.rooms == data.roomNo){
                    item.socket.emit('outChatRoomMsg', data.clientId);
                 }
              });
              if(!isMultiChatRoom(data.roomNo)){
                 removeReject(data.roomNo);
              }
           }else{
              //대화방에 사람이 없으면 대화방 제거
              deleteMessageListElement(data.roomNo);
           }
        }
     });
    //대화방 생성 이벤트
    socket.on('createChatRoom', function(){
    	var roomNo = createChatRoomNo();
    	socket.emit('newChatRoomNo', roomNo);
    });
    //귓속말 이벤트
    socket.on('sendWhisper', function(data){
    	//대화리스트에 저장해야함
    	if(isMultiChatRoom(data.roomNo)){
    		sendWhisper(data.to, data.from, data.roomNo, data);
    	}
    });
    
  //db에 history저장
	socket.on('storeArea',function(data){
		console.log('저장할:',data);
		db.history.findOne({
			userId:data.clientId,
			roomNo : data.roomNo
		},function(err,doc){
			if(data.msg){
				if(doc!==null){
					console.log('이미 저장되어 있지요오오오'+doc);
					db.history.update(
						{ userId: data.clientId,
							roomNo : data.roomNo} ,
							{ $set: { msg: data.msg } },
							{ multi: true }
							);
				}
				else{
					console.log('새로저자자아아아아앙'+doc);
					db.history.insert({userId:data.clientId, msg:data.msg, roomNo:data.roomNo });
				}
			}
		});
	});
	
	//db에서 area get
	socket.on('getArea',function(data){
	      console.log('겟에리아');
	      db.history.findOne({
	         userId:data.clientId,
	         roomNo : data.roomNo
	      },function(err,doc){
	    	  if(!isJoinInfo(data.clientId, data.roomNo)){
		    	  var target = searchMessageListElement(data.roomNo);
		          if(target != null){
		        	  target.flagList.push(data.clientId);
		          }
		          var list = searchJoinInfoListByRoomNo(data.roomNo);
	
		          if(list != null){
		        	  list.forEach(function(item, index){
		        		  var temp = item.socket;
		        		  if(temp.name != data.clientId){
		        			  var target = searchJoinListTarget(temp.name, temp.rooms);
		                      if(target != null){
		                         item.socket.emit('confirmMsg',data.clientId, target.lastRead);
		                      }
		        		  }
		              });
		           }
		         var msgList = getUnreadMsg(data.clientId, data.roomNo, searchJoinListTarget(data.clientId, data.roomNo).lastRead);
		         if(msgList != null){
		        	 deleteMsgList(data.roomNo);
		         }
		         var msg = null;
		         if(doc != null){
		        	 msg = doc.msg;
		         }
		         socket.emit('getArea',msg, msgList);
	    	  }
	      });
	   });
	
    //원래는 없지만 테스트용으로 만듦(현재접속자 뿌려주는거)
/*    socket.on('requestInviteList', function(clientId){
    	var list = new Array();
    	logSockets.forEach(function(item, index){
    		if(item.name != clientId){
    			list.push(item.name);
    		}
    	});
    	socket.emit('responseInviteList', list);
    });*/
    
    socket.on('changMsg', function(data){
    	var list = searchJoinListByRoomNo(data.roomNo);
	    if(list.length <= 2){
	    	var target = searchMessageListElement(data.roomNo);
	    	target.msgList.forEach(function(item, index){
	    		if(item.data.key == data.key){	
	    			item.data.message = data.changeMessage;
	    		}
	    	});
    	}
    });
    
    socket.on('unrock', function(){
    	var target = searchMessageListElement(socket.rooms);
    	if(target != null){
    		console.log(target.flagList);
    		target.flagList.forEach(function(item, index){
    			if(item == socket.name){
    				target.flagList.splice(index, 1);
    			}
    		});
    		if(target.flagList.length == 0){
    			var list = searchJoinInfoListByRoomNo(socket.rooms);
    			if(list != null){
    				list.forEach(function(item, index){
    					if(item.socket.name != socket.name){
    						item.socket.emit('unrock');
    					}
    				});
    			}
    		}
    	}
    });
    
  //친구 추가   
    socket.on("addUser", function(data){
       console.log("날라왔어!!");
       console.log(data);
       var memberId = data.memberId;
       var friendId = data.friendId;
       console.log("1"+memberId);
       console.log("2"+friendId);
       var user = searchLogSocket(memberId);
       console.log("3"+user);
  
       db.userTable.findOne({
          _id : friendId
          },
          function(err,data){
             user.emit("addUser", {
                nickName : data.profile.nickName,
                profilePicture : data.profile.profilePicture,
                statusMsg : data.profile.statusMsg
             });
       });
    });
    
});

//--------------관리자 메뉴 --------------------------------------
//방정보 객체 생성


//로그객체 생성
function createLogInfo(clientId,loginTime){
	var log = {
			clientId:clientId,
			loginTime:loginTime,
			logoutTime:null //로그아웃 시점에 설정
	};
	return log;
}

//사용자의 로그아웃 기록 저장 ( logging 배열 -> logout 배열)
function moveLogout(logging,logout,clientId){
	logging.forEach(function(item,index){
		if(item!=null){
			if(item.clientId==clientId){
				var log=logging[index];
				log.logoutTime=new Date().format('yyyy.MM.dd/hh:mm:ss');
				logging.remove(index);
				logout.push(log);
				//두줄을 -> splice(index) 로 한꺼번에
				//break;
			}
		}
	});
}

//서버에 저장된 logout한 사용자의 로그기록 db에 저장.
function setLogInfoToDb(logout){
	if(logout!=null){
		if(logout.length!=0){
			for(var i in logout){
			//logout.forEeach(function(item,index){
				db.logging.insert({
					clientId: i.clientId,
					loginTime: i.loginTime,
					logoutTime:i.logoutTime
				});
			}
			logout={};
			console.log("서버에 저장된 logout한 사용자의 로그기록 db에 저장.");
		}
	}
}

//개인별 누적접속 횟수 호출
function getClientLoginCount(clientId){
	setLogInfoToDb();
	var cnt=0;
	cnt=db.logging.count( { ugerId: clientId } );
	return cnt;
}

//특정 날짜동안의 접속회원수 db에서 get (날짜 format : 2014.01.25/13:02:40 ) - 2014.01.25 로 입력받음.
//오늘날짜의 전날까지만 입력받을 수 있다.
function getLogCntByDb(sDate,eDate){
	console.log("특정 날짜동안의 접속회원수 db에서 get");
	var loggingReport=[];
	
	db.logging.find({ 
		loginTime: { $gte: sDate, $lte: eDate } 
	}).forEach(function(err,doc){
		if(doc!=null){
			var log=createLogInfo(doc.clientId,doc.loginTime);
			loggingReport.push(log);
		}
	});
	
	return loggingReport;
}

//현재 접속중인 회원수 get
function getCurrentLogging(logging){
	console.log("//현재 접속중인 회원수 get");
	return logging.length;
}
//--------------관리자 메뉴 끝--------------------------------------




//대화방 참여 목록이 바뀌면 리스트 갱신
function synchList(roomNo){
	var list = searchJoinListByRoomNo(roomNo);
	if(list != null){
		joinInfoList.forEach(function(item, index){
			if(item.socket.rooms == roomNo){
				item.socket.emit('list', list);
			}
		});
	}
}
//대화방 목록을 생성
function synchChatRoomList(clientId){
	var list = searchJoinListByid(clientId);
	var rooms = new Array();
	if(list != null){
		list.forEach(function(item, index){
			var joinerList = new Array();
			joinList.forEach(function(sitem, sindex){
				if(item.rooms == sitem.rooms){
					joinerList.push(sitem.clientId);
				}
			});
			rooms.push(createChatRoomInfo(item.rooms, joinerList));
			joinerList = null;
		});
	}else{
		rooms = null;
	}
	return rooms;
}
//해당 사람에게 대화방 목록 뿌림
function sendChatRoomList(clientId, ChatRoomList){
	var target = searchLogSocket(clientId);
	if(target != null){
		target.emit('chatRoomList', ChatRoomList);
	}
}

//참여 대화방의 정보
function createChatRoomInfo(roomNo, joinList){
	var object = {
			roomNo: roomNo,
			joinList: joinList
	};
	return object;
}

/////////////////////
//해당 방의 전체 참여자 목록 가져오기
function searchJoinListByRoomNo(roomNo){
	var list = new Array();
	joinList.forEach(function(item, index){
		if(item.rooms == roomNo){
			list.push(item);
		}
	});
	if(list.length == 0){
		list = null;
	}
	return list;
}
//사용자가 참여 중인 대화방 목록 가져오기
function searchJoinListByid(clientId){
	var list = new Array();
	joinList.forEach(function(item, index){
		if(item.clientId == clientId){
			list.push(item);
		}
	});
	if(list.length == 0){
		list = null;
	}
	return list;
}
//사용자가 참여중인 대화방 참여 정보 가져오기
function searchJoinListTarget(clientId, roomNo){
	var target = null;
	joinList.forEach(function(item, index){
		if(item.clientId == clientId && item.rooms == roomNo){
			target = item;
		}
	});
	return target;
}
//해당 방 번호의 참여자들을 모두 제거
function deleteJoinByRoomNo(roomNo){
	for(var i = 0 ; i < joinList.length ; i ++){
		if(joinList[i].rooms == roomNo){
			joinList.splice(i, 1);
			i--;
		}
	}
}
//해당 사용자의 대화방 참여를 모두 제거
function deleteJoinById(clientId){
	for(var i = 0 ; i < joinList.length ; i ++){
		if(joinList[i].clientId == clientId){
			joinList.splice(i, 1);
			i--;
		}
	}
}
//해당 접속 지우기
function deleteJoin(clientId, roomNo){
	joinList.forEach(function(item, index){
		if(item.clientId == clientId && item.rooms == roomNo){
			joinList.splice(index, 1);
			return;
		}
	});
}
//대화방 참여 객체 생성
function createJoin(clientId, roomNo){
	var object = {
		clientId: clientId,
		rooms: roomNo,
		rejection: null,
		lastRead: new Date().format('yyyy.MM.dd/hh:mm:ss/ms')
	};
	return object;
}
//현재 사용자가 참여중인 대화방 목록 가져오기
function searchJoinInfoListByRoomNo(roomNo){
	var list = new Array();
	joinInfoList.forEach(function(item, index){
		if(item.socket.rooms == roomNo){
    		list.push(item);
    	}
	});
	if(list.length == 0){
		list = null;
	}
	return list;
}

//사용자가 참여 중인 대화방 목록 가져오기
function searchJoinInfoListByRoomNo(roomNo){
	var list = new Array();
	joinInfoList.forEach(function(item, index){
		if(item.socket.rooms == roomNo){
    		list.push(item);
    	}
	});
	if(list.length == 0){
		list = null;
	}
	return list;
}
//사용자가 참여중인 대화방 참여 정보 가져오기
function searchJoinInfoListTarget(clientId, roomNo){
	var target = null;
	joinInfoList.forEach(function(item, index){
		if(item.socket.name == clientId && item.socket.rooms == roomNo){
			target = item;
		}
	});
	return target;
}

//해당 방 번호의 참여자들을 모두 제거
function deleteJoinInfoByRoomNo(roomNo){
	for(var i = 0 ; i < joinList.length ; i++){
		if(joinInfoList[i].socket.rooms == roomNo){
			joinInfoList.splice(i, 1);
			i--;
		}
	}
}
//해당 사용자의 대화방 참여를 모두 제거
function deleteJoinInfoById(clientId){
	for(var i = 0 ; i < joinList.length ; i++){
		if(joinInfoList[i].socket.name == clientId){
			joinInfoList.splice(i, 1);
			i--;
		}
	}
}
//해당 접속 지우기
function deleteJoinInfo(clientId, roomNo){
	console.log('deleteJoinInfo : ' + clientId + ' ' + roomNo)
	joinInfoList.forEach(function(item, index){
		if(item.socket.name == clientId && item.socket.rooms == roomNo){
			console.log(joinInfoList + '\n');
			joinInfoList.splice(index, 1);
			console.log(joinInfoList + '\n');
		}
	});
}

//현재 대화방 소켓 연결된 객체 생성
function createJoinInfo(socket){
	var object = {
			socket: socket,
			rejection: null
			//threadFlag: false
	};
	return object;
}
//로그목록중 id에 해당하는 사용자 가져오기
function searchLogSocket(clientId){
	var target = null;
	logSockets.forEach(function(item, index){
		if(item.name == clientId){
			target = item;
		}
	});
	return target;
}

/////////////////////

//소켓이 끊어질 때 마지막으로 메시지 읽은 시간을 저장한다
function saveLastReadDate(clientId, roomNo, date){
	var target = searchJoinListTarget(clientId, roomNo);
	if(target != null){
		target.lastRead = date;
	}
}

//대화방 목록 변경시 최신화
function updateRooms(clientId){
	var roomList = searchJoinInfoListById(clientId);
	if(roomList != null){
		logSockets.forEach(function(item, index){
			if(item.name == clientId){
				item.emit('updateRooms', roomList);
			}
		});
	}
}

//같은 사람이 같은 방에 접속되어 있는지 검사
function isJoinInfo(clientId, roomNo){
	if(searchJoinInfoListTarget(clientId, roomNo) == null){
		return false;
	}
	return true;
}

//원래 참여했던 방인지 검사
function isJoined(clientId, roomNo){
	if(searchJoinListTarget(clientId, roomNo) == null){
		return false;
	}
	return true;
}

//로그인 되어 있는지 검사
function isLog(clientId){
	if(searchLogSocket(clientId) == null){
		return false;
	}
	return true;
}

//수신거부
function reject(clientId, roomNo){
	var join = searchJoinListTarget(clientId, roomNo);
	if(join != null){
		if(join.clientId == clientId && join.rooms == roomNo){
			if(join.rejection == 'reject'){
				join.rejection = null;
			}else{
				join.rejection = 'reject';
			}
		}
		var joinInfo = searchJoinInfoListTarget(clientId, roomNo);
		if(joinInfo != null){
			if(joinInfo.rejection == 'reject'){
				joinInfo.rejection = null;
			}else{
				joinInfo.rejection = 'reject';
			}
		}
	}
}

//로그아웃시 로그인목록(메인)에서 제거
function deleteLogSocket(clientId){
	logSockets.forEach(function(item, index){
		if(item.name == clientId){
			logSockets.splice(index, 1);
		}
	});
}

//대화방 목록
function chatRoomList(clientId){
	var chatRooms = new Array();
	joinList.forEach(function(item, index){
		var temp = item;
		if(item.clientId == clientId){
			joinList = searchJoinListByRoomNo(temp.rooms);
			chatRooms.push(createChatRoomInfo(temp.rooms, new Array()));
		}
	});
	return chatRooms;
}

//새로운 대화방 번호 생성
function createChatRoomNo(){
	var num = 0;
	while(true){
		if(searchJoinListByRoomNo(num) == null){
			break;
		}else{
			num++;
		}
	}
	return num;
}

//귓속말
function sendWhisper(to, from, roomNo, data){
	var list = searchJoinListByRoomNo(roomNo);
	
	if(list != null && list.length > 2){
		
		list.forEach(function(item, index){
			if(item.clientId == to && item.rooms == roomNo){
				var target = searchJoinInfoListTarget(to, roomNo);
				if(target == null){
					var mTarget = searchMessageListElement(roomNo);
					if(mTarget != null){
						var ttemp = createMsgInfo(true, data, 1);
						mTarget.msgList.push(ttemp);
					}
				}else{
					target.socket.emit('sendWhisper', data, 0);
				}
			}else if(item.clientId == from && item.rooms == roomNo){
				var temp = searchJoinInfoListTarget(from, roomNo);
				var cnt = 0;
				if(searchJoinInfoListTarget(to, roomNo) == null){
					cnt = 1; 
				}
				temp.socket.emit('sendWhisper', data, cnt);
			}
		});
	}
}
//대화방 나가기
function outChatRoom(clientId, roomNo){
	deleteJoinInfo(clientId, roomNo);
	deleteJoin(clientId, roomNo);
	if(searchJoinListByRoomNo(roomNo) == null){
		deleteMessageListElement(roomNo);
	}
}

//현재 읽지 않은 사람 수
function countRead(roomNo){
	var reader = 0;
	var realCount = 0;
	
	joinInfoList.forEach(function(item, index){
		if(item.socket.rooms == roomNo){
			reader++;
		}
	});
	joinList.forEach(function(item, index){
		if(item.rooms == roomNo){
			realCount++;
		}
	});
	return realCount - reader;
}

//수신거부 상태인지 확인
function isReject(clientId, roomNo){
	var target = searchJoinInfoListTarget(clientId, roomNo);
	if(target != null){
		if(target.rejection == 'reject'){
			return true;
		}
	}
	return false;
}

//단체방인지 검사
function isMultiChatRoom(roomNo){
	var num = 0;
	joinList.forEach(function(item, index){
		if(item.rooms == roomNo){
			num++;
		}
	});
	if(num > 2){
		return true;
	}
	return false;
}
//단체방에서 1:1대화방으로 바뀌었을 때 수신거부를 풀어주는 함수
function removeReject(roomNo){
	joinList.forEach(function(item, index){
		if(item.rooms == roomNo && item.rejection == 'reject'){
			item.rejection = null;
			var target = searchJoinInfoListTarget(item.clientId, roomNo);
			if(target != null){
				target.rejection = null;
			}
		}
	});
}

/* ------------------------------------------------------ */
//메시지가 올 때마다 저장시킬 객체생성
function createMsgInfo(whisperFlag, data, unreadNo){
	return {
		whisperFlag: whisperFlag,
		data: data,
		unreadNo: unreadNo
	};
}
//대화방이 생성될때 생성, 대화방 제거될 때 제거
function createMsgListElement(roomNo){
	return {
		roomNo: roomNo,
		msgList: new Array(),
		flagList: new Array()
	};
}

function searchMessageListElement(roomNo){
	var target = null;
	messageList.forEach(function(item, index){
		if(item.roomNo == roomNo){
			target = item;
			return;
		}
	});
	return target;
}

function deleteMessageListElement(roomNo){
	messageList.forEach(function(item, index){
		if(item.roomNo == roomNo){
			messageList.splice(index, 1);
			return;
		}
	});
}
//다 읽은 메시지 날리기
function deleteMsgList(roomNo){
	var count = 0;
	var target = searchMessageListElement(roomNo);
	var list = target.msgList;
	list.forEach(function(item, index){
		if(item.unreadNo == 0){
			count++;
		}
	});
	list.splice(0, count);
}
//지난 메시지 가져오기
function getUnreadMsg(clientId, roomNo, key){
	var target = searchMessageListElement(roomNo);
	var list = target.msgList;
	var getMsgList = new Array();
	list.forEach(function(item, index){
		if(item.data.key >= key && item.unreadNo > 0){	
			if(item.whisperFlag == true){
				if(item.data.to == clientId){
					getMsgList.push(item);
					item.unreadNo--;
				}
			}else{
				getMsgList.push(item);
				item.unreadNo--;
			}
		}
	});
	if(getMsgList.length == 0){
		getMsgList = null;
	}
	return getMsgList;
}

//부재 메시지 갯수
function getUnreadMsgNo(clientId, roomNo, key){
	var num = 0;
	var target = searchJoinListTarget(clientId, roomNo);
	if(target != null){
		var ele = searchMessageListElement(target.rooms);
		ele.msgList.forEach(function(item, index){
			if((item.data.key >= key && item.unreadNo > 0)){
				if(item.whisperFlag == true){
					if(item.data.to == clientId){
						num++;
					}
				}else{
					num++;
				}
			}
		});
	}
	return num;
}

//이미 생성된 대화방인지 검사
function isCreatedRoom(roomNo){
	if(searchMessageListElement(roomNo) != null){
		return true;
	}
	return false;
}
//현재 방 참여자중 지난 메시지 로딩중인 참여자가 있는지 검사
function isNotFlag(roomNo){
	var target = searchMessageListElement(roomNo);
	if(target != null && target.flagList.length == 0){
		return true;
	}
	return false;
}

// 파일 보낼때 서버에서 메시지 형태 만들어줌
function createFileMessage(clientId, date, key){
	var object = {
		clientId: clientId, //속성값 정해줌
        message: aFilePath,
        date: date,
        key: key
     };
	return object;
}

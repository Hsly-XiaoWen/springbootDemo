Java的网络编程Socket编程，分为同步堵塞（BIO）、同步非阻塞(NIO)、异步非阻塞(AIO)

同步堵塞（BIO）
 我们熟知的Socket编程就是BIO，一个socket连接一个处理线程（这个线程负责这个Socket连接的一系列数据传输操作）。
 阻塞的原因在于：操作系统允许的线程数量是有限的，多个socket申请与服务端建立连接时，服务端不能提供相应数量的处理线程，
 没有分配到处理线程的连接就会阻塞等待或被拒绝。
 
同步非阻塞(NIO) 
  New IO是对BIO的改进，基于Reactor模型。我们知道，一个socket连接只有在特点时候才会发生数据传输IO操作，
  大部分时间这个“数据通道”是空闲的，但还是占用着线程。NIO作出的改进就是“一个请求一个线程”，在连接到服务端的众多socket中，
  只有需要进行IO操作的才能获取服务端的处理线程进行IO。这样就不会因为线程不够用而限制了socket的接入。客户端的socket连接到服务端时，
  就会在事件分离器注册一个 IO请求事件 和 IO 事件处理器。在该连接发生IO请求时，IO事件处理器就会启动一个线程来处理这个IO请求，
  不断尝试获取系统的IO的使用权限，一旦成功（即：可以进行IO），则通知这个socket进行IO数据传输。
  
异步非阻塞(AIO)
   NIO是同步的IO，是因为程序需要IO操作时，必须获得了IO权限后亲自进行IO操作才能进行下一步操作。AIO是对NIO的改进
(所以AIO又叫NIO.2），它是基于Proactor模型的。每个socket连接在事件分离器注册 IO完成事件 和 IO完成事件处理器。程序需要进行IO时，
向分离器发出IO请求并把所用的Buffer区域告知分离器，分离器通知操作系统进行IO操作，操作系统自己不断尝试获取IO权限并进行IO操作
(数据保存在Buffer区），操作完成后通知分离器；分离器检测到 IO完成事件，则激活 IO完成事件处理器，处理器会通知程序说“IO已完成”，
程序知道后就直接从Buffer区进行数据的读写。
 
也就是说：AIO是发出IO请求后，由操作系统自己去获取IO权限并进行IO操作；NIO则是发出IO请求后，由线程不断尝试获取IO权限，
获取到后通知应用程序自己进行IO操作。

channl：通道，类似于流，channel允许双向异步读写的，而流是单向的
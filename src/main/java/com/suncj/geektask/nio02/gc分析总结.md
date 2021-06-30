###1、串行垃圾收集器(UseSerialGC)
``java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``

``java -XX:+UseSerialGC -Xms1g -Xmx1g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``

总结:年轻代和老年代都使用的是串行收集处理,串行GC入堆内存比较大情况下,进行一次FullGc会很耗时,因为是单线程STW,如果堆内存比较小可能会导致GC频率比较频繁,服务端一般是不会采用串行GC,业务吞吐量低
适用于:单核服务器,物理内存比较小一些的配置,比如1核2G,单核CPU没法并行,开多线程处理GC还会出现线程切换竞争问题,效率更低
###2、并行垃圾收集器(ParallelGC)
``java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``

``java -XX:+UseParallelGC -Xms1g -Xmx1g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``
总结:年轻代使用标记-复制算法,老年代使用标记-清除-整理算法,年轻代和老年代的垃圾回收时都会触发STW事件,暂停所有的应用线程,在执行标记和整理阶段时都使用多线程,通过多个GC线程并行的方式,能使GC时间大幅减少
适用于:多核服务器,对系统延迟性能指标要求不那么高的情况,目标是增加系统吞吐量,降低GC总体消耗时间,由于并行GC的所有阶段都有不能中断,所以并行GC很可能会出现长时间的卡顿。

###3、并发标记清除垃圾收集器(CMS GC)
``java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``

``java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``
总结:年轻代默认是并行垃圾收集器,老年代是并发标记清除垃圾收集器,老年代没有整理,所以会出现内存碎片,设计目标是避免在老年代GC出现长时间的卡顿,默认情况下,CMS使用的并发线程线程数等于CPU内核数的1/4,如果CPU资源受限,CMS吞吐量会比并行GC差一些,
适用于:多核服务器,内存不是很大的情况(比如8G以下)对系统延迟性能指标要求不那么高的情况,由于老年代会出现内存碎片问题,在某些情况需要分配连续地址的对象数据时,会没有足够的空间,GC会出现不可预测的暂停时间,特别是堆内存比较大时

###4、G1垃圾收集器
``java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``

``java -XX:+UseG1GC -Xms1g -Xmx1g -Xloggc:gc.demo.log -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps com.suncj.geektask.nio02.GCLogAnalysis``
总结:垃圾优先,哪一块的垃圾最多就优先清理,在堆内存较大情况(8G+),如果G1发生FullGC,暂停时间可能会退化成串行GC,停顿时间达到几十秒甚至更多,G1是自适应的增量垃圾收集器,一般来说只有在内存严重不足的情况下才会发生FullGC
适用于:多核服务器,内存不是很大的情况(比如8G以下),对GC暂停时间有严格要求
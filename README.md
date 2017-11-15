# androidMultiThreadingBehaviour
           MultiThreading:
so to understand MultiThreading we must know the bases.
-Every app we open in android gets a dedicated process ,no matter how big or small it is.
-the process contains mini processes called threads.
-a process can have any number of threads.
-out of these threads one is the main/UI thread.
-by default a complete app is run by a process on this main/UI thread.
-the app only uses another thread only if we wants it to i.e we would explicitly create a separate thread or use some thing that do this.
-so all the UI related stuff like rendrening etc also happens on UI thread.
-now what will happen if a heavy work like making a network call and then wait for results comes along.
-and at the user also starts interacting with the UI ,a single thread will not be able to keep up and UI will be blocked.
-so for that reason we need to transfer any heavy work like network call to a separate thread so that user can continue on using the UI.
-android have provided multiple api's like AsyncTask and Loaders to save us from this complex stuff of creating and managing separate thread.
-but right now we are jumping into whats under the hood of these api's,to know the complex stuff and use these api's better.

---------------------how to create a new thread--------------------
-simply make an instance of Thread class an pass it a runnable object which is actually a task for this thread.
-the runnable object has a function called run() ,post your task in it,now call method start on this thread instance and thread will start.
-now the default behaviour of any thread including the UI thread is that ,it will execute the task provided and then terminate it self.
-which is confusing as the UI thread will complete loading the app then what will it terminate itself and not wait for a new task like button click.
-surely their is some catch here!
-indeed their is.lets dive in.
-----------------Structur of Main/UI thread.........................
the main/UI thread consist of following components ,each has a specific purpose.
a message queue, looper ,handler
Mesage queue:
            message queue is the queue of task ,these tasks can be button click,setContentView etc any thing happening in app is put as a 
            task in this message queue,this queue of messages is executed on bases of first in first out.remember these jobs/task are to
            be executed on UI thread as this message queue belongs to UI thread.
Looper:
         looper solves the problem that we have already discussed. android normal thread terminats it self no more task left.now about
         the UI thread ,it can't terminate itselft because it has to wait for user input tasks like button click etc.Looper gives a
         solution by looping the message queue ,in other word it does't let it finish, because its in loop ,because of which thread
         is gonna think their is always a message in the queue. so my assumption is looper does this by putting dummy messages in the 
         queue. Looper also ready these messages and give them to handler ,so handler can execute them.

Handler:
        handler executes the tasks/messages in message queue and also loads tasks/messages in that queue.
        now this is confusing why would it load task in a queue. its supposed to execute them.
        well it does this from another thread ,because its handler of thread so it does this thing too.
        so if we wan't the UI thread to execute something from the separate thread ,simply get the handler of UI thread and call
        handler.post(task) on it. handler will post this task to the message queue of the UI thread. and when the time comes execute
        it when looper hand's that message over to the handler.
        
         
rules:
      only UI thread can work on its views . other threads can't update the views created in UI thread, so they have to call the
      handler of the UI thread and tells it post a UI related task to its message queue.























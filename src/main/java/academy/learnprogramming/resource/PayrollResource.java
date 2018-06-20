package academy.learnprogramming.resource;

import academy.learnprogramming.service.PayrollService;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

@Path("payroll")
public class PayrollResource {

    @Resource
    ManagedExecutorService managedExecutorService;

    @Inject
    PayrollService payrollService;


    @POST
    @Path("run")
    public void run(@Suspended AsyncResponse asyncResponse) {
        final String currentThread = Thread.currentThread().getName();
        //1. Implement timeout handler

        //2. Register other callbacks

        //3. Pass long running task to MES and resume in there
        managedExecutorService.submit(()->{
            final String spawnedThreadName = Thread.currentThread().getName();
            //Long running task
            payrollService.computePayroll();
            asyncResponse.resume(Response.ok().header("Original Thread", currentThread)
                    .header("Spawned Thread", spawnedThreadName)
                    .status(Response.Status.OK).build());


        });




    }

    static class CompletionCallbackHandler implements CompletionCallback{

        @Override
        public void onComplete(Throwable throwable) {

        }
    }

    //Optional for implementations. Check with your provider!
    static class ConnectionCallbackHandler implements ConnectionCallback{

        @Override
        public void onDisconnect(AsyncResponse disconnected) {

        }
    }


    @POST
    @Path("run-cf")
    public void computePayrollCF(@Suspended AsyncResponse asyncResponse, @QueryParam("i") @DefaultValue("3") long number) {

        CompletableFuture.runAsync(() -> payrollService.fibonacci(number), managedExecutorService)

                .thenApply(asyncResponse::resume);
    }
}

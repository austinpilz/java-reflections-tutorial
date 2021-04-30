# The Action Tutorial
## 1 - The Service
The application in this project is a standalone Java 11 Spring microservice that exposes a single API endpoint which allows 
you to perform a set of dynamic actions.

![Action UI Architecture](/images/ActionUIArchitecture.png)

The inspiration for the service was the "back end" of a UI - meaning there was a front-front end (e.g. Angular) which would 
be bundled with and call this service in order to have it perform some work (call other services, do some translations, etc.) 
Professionally I've come across multiple UIs which were unable to be truly independent (i.e. calling back end services directly) 
due to unique security considerations, mapping libraries, etc. In this scenario, it just made sense to have a front-front 
end, and a front-back end bundled together to carry out the full scope of "front end" UI responsibilities. While that's the 
inspiration, you can apply this setup anywhere in any Java app you wanted to. This tutorial will refer to the inspiration 
as the setup in order to have a consistent theme for you.

## 2 - The Fictitious UI
In this example, our Java service is the "back end" processor for our front end UI. The design would be the front end IU, 
let's say Angular, would call back to this packaged Java service to have it perform a set of actions. The UI itself isn't 
implemented in the slightest, we just know we'll be working with it.

Let's pretend our UI is a banking application. It'll allow employees/customers to open their account, check their balance,
close their account, etc. This UI has a very defined set of actions, it's not necessarily procedural. For example, we have
to open the account before I can check the balance. Once I'm done, I should close the account so someone else can access.
With that in mind, from the UI I'll likely be having to do a lot of "Open Account" actions, "Check Balance" actions, and
then finally "Close Account" actions.

## 3 - Traditional API
Here we'll cover how you might logically build the API to support all of our defined UI actions we looked at above.

Every time we define an action (UI feature) which we need to have back end support for, here's the process we'd have to
take doing it traditionally. Here we'll use the "Open Account" action as an example:

1) Create Front End object used to pass information (via JSON) to the back end. For example, `OpenAccountRequest.ts`,
   this is how the front end would initiate the request from itself into it's back end.
2) Create Back End object used to receive action request to be mapped from JSON. For example, `OpenAccountRequest.java`,
   this is how Java (via Jackson) would map the JSON request from the front end into a usable object to process the request.
3) Create Back End endpoint for that specific action, ex `/api/actions/open_account`, which accepts the request object we
   made in Step 2.
4) Implement the Java code which would do all the implementation-specific processing required for that action, like calling
   an account service or something.
5) Create Back End object used to transmit the response of the action invocation. For example, `OpenAccountResponse.java`,
   this is how we could store the outcome of the action (like the account number, the account name, the error message,
   the lock code, etc.) This object will be returned from the back end and converted into JSON as a response to the Front End.
6) Create Front End object used to receive and map the response from the back end so it can display the information to the user.

Phew. Look at all of those steps for just one action. Imagine you build out a beefy banking UI where we can view our account,
add beneficiaries, stake our crypto, perform retirement analysis, dispute overdraft fees, etc. It's evident I've never
worked in the financial industry, but you get the idea. Every time we wanted to add something new, there would be a huge
amount of engineering time dedicated just to getting it, so we can even perform the action in the UI, let alone the actual
performant code in the true back end which actually does what we're asking (like perform crypto staking estimates over time).

Hopefully we can see that over time, we'd be investing a lot of time as engineers doing that overhead for new actions/features.
That's boring for us, expensive for our employer, and leaves a lot of classes/files which all somehow map to one another.
It's just not as scalable as it could be. With that in mind, now let's go take a look at what we can do to make it better.

### 4 - Better API

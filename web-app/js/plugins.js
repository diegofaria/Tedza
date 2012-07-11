/* miere's (c xavier) motherfocker javascript */
//JS Unit Fodao do TedZa
function assertEquals( v, v2, msg ) {
	if ( v != v2 ) {
		throw msg || (v + " != " + v2);
	}
}

// StateMachine
var StateMachine = function ( args ) {
	this.next = function () {
		if (cursor < objects.length - 1) 
			cursor++;
		listener ( objects[cursor] );
	};
	
	this.prev = function () {
		if (cursor > 0)
			cursor--;
		listener( objects[cursor] );
	};
	
	this.goTo = function ( index ) {
		if (index < 0)
			cursor = 0;
		else if (index > objects.length - 1)
			cursor = objects.length - 1;
		else
			cursor = index;
		listener ( objects[cursor] );
	};
	
	var cursor = 0;
	var objects = args.objects;
	var listener =  args.listener;
};

//StateMachine
function grantThatStateMachineWorks() {
	
	var current = null;
	var states = new StateMachine({
		objects: [ 1, 2, 3, 4 ],
		listener: function ( object ) {
			current = object;
		}
	});
	
	states.next();
	assertEquals(2, current);

	states.prev();
	assertEquals(1, current);

	states.prev();
	assertEquals(1, current);
	for ( var i=0; i< 4; i++ )
		states.next();
	
	states.next();
	assertEquals(4, current);
	
	states.goTo( 2 );
	assertEquals(3, current);
	
	states.goTo( -6 );
	assertEquals(1, current);
	
	states.goTo( 6 );
	assertEquals(4, current);

	return "grantThatStateMachineWorks";
}

// Navigation
function grantThatNavigationWorks() {

	var lastShownId = null,
		lastHiddenId = null;

	// assert that start with the first step
	var nav = new Navigation({
		objects: ["div1","div2","div3","div4"]
	});
	nav.show = function ( id ) {
		lastShownId = id;
	};
	nav.hide = function ( id, callback ) {
		lastHiddenId = id;
		if ( callback )
			callback();
	};
	
	assertEquals( "div1", nav.current );

	// assert that hide the first state, and show the next state
	nav.showNextStep();
	assertEquals ( lastHiddenId, "div1" );
	assertEquals ( lastShownId, "div2" );
	
	// assert that show the 4th div and hide the 2nd
	nav.goToStep(3);
	assertEquals ( lastHiddenId, "div2" );
	assertEquals ( lastShownId, "div4" );
	
	return "grantThatNavigationWorks";
}

var Navigation = function ( args ) {
	var self = this;

	self.goToStep = function ( idx ) {
		self.hide( self.current, 
			function (){
				states.goTo( idx );
				self.show( self.current );
				if (args.listener)
					args.listener( self.current );
			});
	};

	self.showNextStep = function () {
		self.hide( self.current, 
			function (){
				states.next();
				self.show( self.current );
				if(args.listener)
					args.listener( self.current );
			});
	};

	self.show = function ( id, callback ) {
		$("#"+id).fadeIn("fast");
	};

	self.hide = function ( id, callback ) {
		$("#"+id).fadeOut("fast", function(){
			if ( callback )
				callback();
		});
	};

	var listener = function ( object ) {
		self.current = object;
		if ( args.listener )
			args.listener( object );
	};

	self.current = null;
	var states = new StateMachine({
		objects: args.objects,
		listener: listener
	});

	states.goTo(0);
	self.show( self.current );
};

$(function() {
	console.log( "Running unit tests..." )
	
	var tests = [
         grantThatStateMachineWorks,
         grantThatNavigationWorks
     ];
	
	for (var i=0; i<tests.length; i++)
		console.log( tests[i].call() );
});
$(function() {
    var linearScale, getPointsArray, resize, waveform, ractive;

    // this returns a function that scales a value from a given domain
    // to a given range. Hat-tip to D3
    linearScale = function ( domain, range ) {
        var d0 = domain[0], r0 = range[0], multipler = ( range[1] - r0 ) / ( domain[1] - d0 );

        return function ( num ) {
            return r0 + ( ( num - d0 ) * multipler );
        };
    };

    // this function takes an array of values, and returns an array of
    // points plotted according to the given x scale and y scale
    getPointsArray = function ( array, xScale, yScale ) {
        var result = array.map( function ( value, i ) {
            return xScale( i + 0.5 ) + ',' + yScale( value );
        });

        // add the december value in front of january, and the january value after
        // december, to show the cyclicality
        //result.unshift( xScale( -0.5 ) + ',' + yScale( array[ array.length - 1 ][ point ] ) );
        //result.push( xScale( array.length + 0.5 ) + ',' + yScale( array[0][ point ] ) );

        return result;
    };

    ractive = new Ractive({
        el: "#waveformContainer",
        template: "#waveform",
        data: {
            format: function ( val ) {
                return val.toFixed( 1 );
            },

            // this function returns the SVG string for the polyline representing the
            // waveform
            band: function ( data ) {
                var xScale, yScale, high = [], low = [];

                xScale = this.get( 'xScale' );
                yScale = this.get( 'yScale' );

                high = getPointsArray( data, xScale, yScale, 'high' );
                //low = getPointsArray( months, xScale, yScale, 'low' );

                return high.join( ' ' );
            },

            monthNames: [ 'J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D' ]
        },
        onrender: function() {
            var that = this;

            this.on({
                updateWaveform: function() {
                    var index = $("#waveformIndex").val() || 0;
                    var value = $("#waveformValue").val() || 0;
                    var waveform = this.get('waveform');
                    waveform[index] = value;
                    this.set('waveform', waveform);
                }
            });
        }
    });


    // because we're using SVG, we need to manually redraw
    // when the container resizes. You *can* use percentages
    // instead of pixel/em lengths, but not in transforms
    resize = function () {
        var width, height;

        width = ractive.nodes.svg_wrapper.clientWidth;
        height = ractive.nodes.svg_wrapper.clientHeight;

        ractive.set({
            width: width,
            height: height
        });
    };

    // recompute xScale and yScale when we need to
    ractive.observe({
        width: function ( width ) {
            this.set( 'xScale', linearScale([ 0, 12 ], [ 0, width ]) );
        },
        height: function ( height ) {
            this.set( 'yScale', linearScale([ -10, 42 ], [ height - 20, 25 ]) );
        }
    });

    // update width and height when window resizes
    window.addEventListener( 'resize', resize );
    resize();


    // respond to user input
    //ractive.observe( 'selected', function ( index ) {
    //    this.animate( 'selectedCity', cities[ index ], {
    //        easing: 'easeOut',
    //        duration: 300
    //    });
    //});


    waveform = [1, 33, 5, 7, 41, 10, 15];

    ractive.set({
        waveform: waveform
    });

    $("#waveformValue").TouchSpin({
        max: 0x7fffffff,
        boostat: 5,
        verticalbuttons: true
    });

    $("#waveformIndex").TouchSpin({
        max: waveform.length,
        boostat: 5,
        verticalbuttons: true
    });
});

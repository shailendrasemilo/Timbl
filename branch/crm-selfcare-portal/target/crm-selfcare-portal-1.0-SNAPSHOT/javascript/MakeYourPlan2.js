( function( $ ){

	/*
	 * Taken from here: http://stackoverflow.com/questions/5623838/rgb-to-hex-and-hex-to-rgb
	 */
	window.hexToRgb = function( hex ){
		var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec( hex );
		return result ? {
			r : parseInt( result[ 1 ], 16 ), g : parseInt( result[ 2 ], 16 ), b : parseInt( result[ 3 ], 16 )
		} : null;
	};
	window.rgbTohex = function( r, g, b ){
		return "#" + ( ( 1 << 24 ) + ( r << 16 ) + ( g << 8 ) + b ).toString( 16 ).slice( 1 );
	};

	/*
	 * Taken from here: http://www.sitepoint.com/javascript-generate-lighter-darker-color/
	 */
	window.colorLuminance = function( hex, lum ){
		// validate hex string
		hex = String( hex ).replace( /[^0-9a-f]/gi, '' );
		if ( hex.length < 6 ) {
			hex = hex[ 0 ] + hex[ 0 ] + hex[ 1 ] + hex[ 1 ] + hex[ 2 ] + hex[ 2 ];
		}
		lum = lum || 0;
		// convert to decimal and change luminosity
		var rgb = "#", c, i;
		for ( i = 0; i < 3; i++ ) {
			c = parseInt( hex.substr( i * 2, 2 ), 16 );
			c = Math.round( Math.min( Math.max( 0, c + ( c * lum ) ), 255 ) ).toString( 16 );
			rgb += ( "00" + c ).substr( c.length );
		}
		return rgb;
	};

	/*
	 * Taken from here: https://github.com/anomal/RainbowVis-JS/blob/master/rainbowvis.js Usage Example : http://stackoverflow.com/questions/3080421/javascript-color-gradient
	 */
	/*
	 * RainbowVis-JS Released under MIT License
	 */
	window.Rainbow = function(){
		var gradients = null;
		var minNum = 0;
		var maxNum = 100;
		var colours = [
				'ff0000', 'ffff00', '00ff00', '0000ff'
		];
		setColours( colours );

		function setColours( spectrum ){
			if ( spectrum.length < 2 ) {
				throw new Error( 'Rainbow must have two or more colours.' );
			}
			else {
				var increment = ( maxNum - minNum ) / ( spectrum.length - 1 );
				var firstGradient = new ColourGradient();
				firstGradient.setGradient( spectrum[ 0 ], spectrum[ 1 ] );
				firstGradient.setNumberRange( minNum, minNum + increment );
				gradients = [
					firstGradient
				];
				for ( var i = 1; i < spectrum.length - 1; i++ ) {
					var colourGradient = new ColourGradient();
					colourGradient.setGradient( spectrum[ i ], spectrum[ i + 1 ] );
					colourGradient.setNumberRange( minNum + increment * i, minNum + increment * ( i + 1 ) );
					gradients[ i ] = colourGradient;
				}
				colours = spectrum;
			}
		}

		this.setColors = this.setColours;
		this.setSpectrum = function(){
			setColours( arguments );
		};
		this.setSpectrumByArray = function( array ){
			setColours( array );
		};
		this.colourAt = function( number ){
			if ( isNaN( number ) ) {
				throw new TypeError( number + ' is not a number' );
			}
			else if ( gradients.length === 1 ) {
				return gradients[ 0 ].colourAt( number );
			}
			else {
				var segment = ( maxNum - minNum ) / ( gradients.length );
				var index = Math.min( Math.floor( ( Math.max( number, minNum ) - minNum ) / segment ), gradients.length - 1 );
				return gradients[ index ].colourAt( number );
			}
		};
		this.colorAt = this.colourAt;
		this.setNumberRange = function( minNumber, maxNumber ){
			if ( maxNumber > minNumber ) {
				minNum = minNumber;
				maxNum = maxNumber;
				setColours( colours );
			}
			else {
				throw new RangeError( 'maxNumber (' + maxNumber + ') is not greater than minNumber (' + minNumber + ')' );
			}
		};
	};
	window.ColourGradient = function(){
		var startColour = 'ff0000';
		var endColour = '0000ff';
		var minNum = 0;
		var maxNum = 100;
		this.setGradient = function( colourStart, colourEnd ){
			startColour = getHexColour( colourStart );
			endColour = getHexColour( colourEnd );
		};
		this.setNumberRange = function( minNumber, maxNumber ){
			if ( maxNumber > minNumber ) {
				minNum = minNumber;
				maxNum = maxNumber;
			}
			else {
				throw new RangeError( 'maxNumber (' + maxNumber + ') is not greater than minNumber (' + minNumber + ')' );
			}
		};
		this.colourAt = function( number ){
			return calcHex( number, startColour.substring( 0, 2 ), endColour.substring( 0, 2 ) )
					+ calcHex( number, startColour.substring( 2, 4 ), endColour.substring( 2, 4 ) )
					+ calcHex( number, startColour.substring( 4, 6 ), endColour.substring( 4, 6 ) );
		};
		function calcHex( number, channelStartBase16, channelEndBase16 ){
			var num = number;
			if ( num < minNum ) {
				num = minNum;
			}
			if ( num > maxNum ) {
				num = maxNum;
			}
			var numRange = maxNum - minNum;
			var cStartBase10 = parseInt( channelStartBase16, 16 );
			var cEndBase10 = parseInt( channelEndBase16, 16 );
			var cPerUnit = ( cEndBase10 - cStartBase10 ) / numRange;
			var cBase10 = Math.round( cPerUnit * ( num - minNum ) + cStartBase10 );
			return window.formatHex( cBase10.toString( 16 ) );
		}
		window.formatHex = function( hex ){
			if ( hex.length === 1 ) {
				return '0' + hex;
			}
			else {
				return hex;
			}
		};

		window.isHexColour = function( string ){
			var regex = /^#?[0-9a-fA-F]{6}$/i;
			return regex.test( string );
		};

		function getHexColour( string ){
			if ( isHexColour( string ) ) {
				return string.substring( string.length - 6, string.length );
			}
			else {
				var colourNames = [
			                        ['red', 'ff0000'],
			                        ['lime', '00ff00'],
			                        ['blue', '0000ff'],
			                        ['yellow', 'ffff00'],
			                        ['orange', 'ff8000'],
			                        ['aqua', '00ffff'],
			                        ['fuchsia', 'ff00ff'],
			                        ['white', 'ffffff'],
			                        ['black', '000000'],
			                        ['gray', '808080'],
			                        ['grey', '808080'],
			                        ['silver', 'c0c0c0'],
			                        ['maroon', '800000'],
			                        ['olive', '808000'],
			                        ['green', '008000'],
			                        ['teal', '008080'],
			                        ['navy', '000080'],
			                        ['purple', '800080']
			                    ];
				for ( var i = 0; i < colourNames.length; i++ ) {
					if ( string.toLowerCase() === colourNames[ i ][ 0 ] ) {
						return colourNames[ i ][ 1 ];
					}
				}
				throw new Error( string + ' is not a valid colour.' );
			}
		}
	};

	window.getColorArray = function( starthex, endhex, shadecount ){
		var result = [];
		var rainbow = new Rainbow();
		rainbow.setNumberRange( 0, shadecount - 1 );
		rainbow.setSpectrum( starthex, endhex );
		for ( var i = 0; i < shadecount; i++ ) {
			// var factor = (i / shadecount) * 0.5;
			// result.push(window.colorLuminance(basehex, factor));
			result.push( rainbow.colourAt( i ) );
		}
		return result;
	};

	$( document )
			.ready(
					function(){
						// Cache Old colors for resetting to defaults
						window.__oldHeadClrs = [];
						window.__oldBodyClrs = [];

						/*
						 * var headAbsDivClass = 'headAbsDiv'; var bodyAbsDivClass = 'bodyAbsDiv';
						 */

						$( '.animateHeadTd' ).each( function( e ){
							// Add custom info to TDs
							$( this ).attr( 'data-index', e );
							$( this ).addClass( inActiveHeadTdClass );
							/*
							 * //Trick: Add a absolute DIV in TD make transition smooth work by fading the DIV. var absDiv = '<div class="' + headAbsDivClass + '" style="position: absolute; top: 0px; left: 0px;"></div>'; $(this).prepend($(absDiv).css( { 'width': $(this).width() + 'px', 'height':
							 * $(this).height() + 'px', 'background-color': $(this).css('background-color') } ));
							 */
							__oldHeadClrs[ e ] = {
								background_color : $( this ).css( 'background-color' ), color : $( this ).css( 'color' )
							};
						} );
						$( '.animateBodyTd' ).each( function( e ){
							// Add custom info to TDs
							$( this ).attr( 'data-index', e );
							$( $( this ).find( 'input[name=tariffAddonPlan]' )[ 0 ] ).attr( 'data-index', e );
							$( this ).addClass( inActiveBodyTdClass );
							/*
							 * //Trick: Add a absolute DIV in TD make transition smooth work by fading the DIV. var absDiv = '<div class="' + bodyAbsDivClass + '" style="position: absolute; top: 0px; left: 0px;"></div>'; $(this).prepend($(absDiv).css( { 'width': $(this).width() + 'px', 'height':
							 * $(this).height() + 'px', 'background-color': $(this).css('background-color') } ));
							 */
							__oldBodyClrs[ e ] = {
								background_color : $( this ).css( 'background-color' ), color : $( this ).css( 'color' )
							};
						} );

						// Top arrow index adding
						$( '.topArrow' ).each( function( e ){
							$( this ).attr( 'data-index', e );
						} );

						var oldBaseHeadBackground = {
							background_color : $( '.animateBaseTd' ).css( 'background-color' ), color : $( '.animateBaseTd' ).css( 'color' )
						};

						// Set the colors below to set Start and End Colors of Shade on Animated TDs
						var animatedHeadLightShade = '#fcecac';
						var animatedBodyLightShade = '#fcecac';
						var animatedHeadDarkShade = '#f5c300';
						var animatedBodyDarkShade = '#f5c300';
						var animatedHeadForeColor = '#1f487c';

						// Cache Elements to save processing :)
						var animateHeadTds = $( '.animateHeadTd' );
						var animateBodyTds = $( '.animateBodyTd' );
						/*
						 * var headAbsDivs = $('.' + headAbsDivClass); var bodyAbsDivs = $('.' + bodyAbsDivClass);
						 */

						var activeHeadTdClass = 'activeHeadTd';
						var inActiveHeadTdClass = 'inActiveHeadTd';
						var activeBodyTdClass = 'activeBodyTd';
						var inActiveBodyTdClass = 'inActiveBodyTd';
						var activeBaseBodyTdClass = 'activeBaseBodyTd';
						var inActiveBaseBodyTdClass = 'inActiveBaseBodyTd';

						var baseAnimationBlocked = false;
						var addOnAnimationBlocked = false;

						window.__activeAnimElemIndex = -1;
						window.__inActiveAnimElemIndex = -1;
						window.__selectedAddonIndex = -1;

						var headShadeColors = getColorArray( animatedHeadLightShade, animatedHeadDarkShade, animateHeadTds.length );
						var bodyShadeColors = getColorArray( animatedBodyLightShade, animatedBodyDarkShade, animateBodyTds.length );

						$( 'input[name=selectedPlanCode]' ).click(
								function(){
									if($(this).hasClass('addon_Y')){
										// Handle Radio buttons
										if ( !$( this ).hasClass( 'valuePlan' ) ) {
											$( '.addOnOverlayDiv' ).hide();
											$( '.topArrow' ).show();
											$( '#arrowNumberIMG' ).show();
											$("#showradiooption").show();
											$( '.noAddonPlanDiv' ).show();
											if ( !baseAnimationBlocked ) {
												$( '#myopNavigatePrevious' ).removeAttr( 'disabled' );
											}

											var currentRow = $( this ).parents( 'tr.animateBaseBodyRow' );

											if ( $( '.' + activeBaseBodyTdClass ).length > 0 ) {
												$( '.' + activeBaseBodyTdClass ).each(
														function( e ){
															$( this ).css(
																	{
																		'background-color' : oldbaseBodyBackground[ e ].background_color,
																		'color' : oldbaseBodyBackground[ e ].color
																	} );
														} ).removeClass( activeBaseBodyTdClass ).addClass( inActiveBaseBodyTdClass );
											}
											$( currentRow ).children().css( 'background-color', animatedBodyDarkShade ).removeClass(
													inActiveBaseBodyTdClass ).addClass( activeBaseBodyTdClass );
											$( currentRow ).children().css( 'color', animatedHeadForeColor );

											if ( $( '.' + activeBodyTdClass ).length < 1 ) {
												$( '.animateBaseTd' ).css( 'background-color', animatedBodyDarkShade );
												$( '.animateBaseTd' ).css( 'color', animatedHeadForeColor );
											}
											baseAnimationBlocked = true;
											$( '#myopValueplanNavigateNext' ).attr( 'disabled', 'disabled' );
										}
										else {
											$( '#myopNavigateNext' ).attr( 'disabled', 'disabled' );
											$( '#myopValueplanNavigateNext' ).removeAttr( 'disabled' );
											$( 'input[name=tariffNoAddOn]' ).removeAttr( 'checked' );
											$( 'input[name=tariffAddonPlan]' ).removeAttr( 'checked' );
											$( '.addOnOverlayDiv' ).show();
											$( '.topArrow' ).hide();
											$( '#arrowNumberIMG' ).hide();
											$( '.noAddonPlanDiv' ).hide();
											baseAnimationBlocked = false;
											$( '.animateBaseBodyRow' ).trigger( 'mouseleave' );
											if ( $( '.' + activeBaseBodyTdClass ).length > 0 ) {
												$( '.' + activeBaseBodyTdClass ).each(
														function( e ){
															$( this ).css(
																	{
																		'background-color' : oldbaseBodyBackground[ e ].background_color,
																		'color' : oldbaseBodyBackground[ e ].color
																	} );
														} ).removeClass( activeBaseBodyTdClass ).addClass( inActiveBaseBodyTdClass );
											}
											$( '.animateBaseTd' ).css( 'background-color', oldBaseHeadBackground.background_color );
											$( '.animateBaseTd' ).css( 'color', oldBaseHeadBackground.color );
										}
									}else{
										$( '.addOnOverlayDiv' ).show();
									}
									
								} );

						$( 'input[name=tariffNoAddOn]' ).click( function(){
							var actionIsCheck = $( this ).attr( 'checked' ) != undefined;
							if ( actionIsCheck ) {
								$( 'input[name=tariffAddonPlan]' ).each( function(){
									$( this ).removeAttr( 'checked' );
								} );
								resetHeadColoring();
								resetBodyColoring();
								$( '.animateBaseTd' ).css( 'background-color', animatedBodyDarkShade );
								$( '.animateBaseTd' ).css( 'color', animatedHeadForeColor );

								$( '#myopNavigateNext' ).removeAttr( 'disabled' );

								window.__selectedAddonIndex = -1;
							}
							else {
								$( '#myopNavigateNext' ).attr( 'disabled', 'disabled' );
							}
							addOnAnimationBlocked = false;
						} );

						$( 'input[name="tariffBasePlan"].valuePlan' ).click( function(){
							var actionIsCheck = $( this ).attr( 'checked' ) != undefined;
							if ( actionIsCheck ) {
								$( 'input[name=tariffAddonPlan]' ).each( function(){
									$( this ).removeAttr( 'checked' );
								} );
								resetHeadColoring();
								resetBodyColoring();
								$( '.animateBaseTd' ).css( 'background-color', oldBaseHeadBackground.background_color );
								$( '.animateBaseTd' ).css( 'color', oldBaseHeadBackground.color );

								window.__selectedAddonIndex = -1;
							}
							else {
								$( '#myopNavigateNext' ).attr( 'disabled', 'disabled' );
							}
							addOnAnimationBlocked = false;
						} );

						$( 'input[name=tariffAddonPlan]' ).click( function(){
							// Handle checkbox
							var currIndex = $( this ).data( 'index' );
							// addOnAnimationBlocked = true;

							var actionIsCheck = $( this ).attr( 'checked' ) !== undefined;

							$( 'input[name=tariffAddonPlan]' ).each( function( e ){
								if ( actionIsCheck ) {
									if ( e <= currIndex )
										$( this ).attr( 'checked', 'checked' );
									else
										$( this ).removeAttr( 'checked' );
								}
								else {
									if ( currIndex > 0 && e < currIndex )
										$( this ).attr( 'checked', 'checked' );
									else
										$( this ).removeAttr( 'checked' );
								}
							} );
							refreshHeadColoring( actionIsCheck ? currIndex : currIndex - 1 );
							refreshBodyColoring( actionIsCheck ? currIndex : currIndex - 1 );

							if ( currIndex == 0 && !actionIsCheck ) {
								addOnAnimationBlocked = false;
								$( '.animateBaseTd' ).css( 'background-color', animatedBodyDarkShade );
								$( '.animateBaseTd' ).css( 'color', animatedHeadForeColor );

								$( '#myopNavigateNext' ).attr( 'disabled', 'disabled' );
							}

							if ( actionIsCheck ) {
								window.__selectedAddonIndex = currIndex;
								$( 'input[name=tariffNoAddOn]' ).removeAttr( 'checked' );
								$( '.animateBaseTd' ).css( 'background-color', oldBaseHeadBackground.background_color );
								$( '.animateBaseTd' ).css( 'color', oldBaseHeadBackground.color );

								$( '#myopNavigateNext' ).removeAttr( 'disabled' );
							}
							else {
								window.__selectedAddonIndex = currIndex - 1;
							}
						} );

						var oldbaseBodyBackground = [];

						$( '.animateBaseBodyRow' ).each( function(){
							$( this ).children().addClass( inActiveBaseBodyTdClass );
						} );

						$( '.animateBaseBodyRow' ).click( function(){
							// if ($('.' + activeBaseBodyTdClass).length > 0) {
							// $('.' + activeBaseBodyTdClass).each(function (e) {
							// $(this).css({
							// 'background-color': oldbaseBodyBackground[e].background_color,
							// 'color': oldbaseBodyBackground[e].color
							// });
							// }).removeClass(activeBaseBodyTdClass).addClass(inActiveBaseBodyTdClass);;
							// }
							// $(this).children().css('background-color', animatedBodyDarkShade).removeClass(inActiveBaseBodyTdClass).addClass(activeBaseBodyTdClass);
							// $(this).children().css('color', animatedHeadForeColor);
							// $('.animateBaseTd').css('background-color', animatedBodyDarkShade);
							// $('.animateBaseTd').css('color', animatedHeadForeColor);
						} );

						$( '#myopNavigatePrevious' ).click( function(){

						} );
						$( '#myopNavigateNext' ).click( function(){
						} );

						$( '.animateBaseBodyRow' ).hover( function(){
							if ( !baseAnimationBlocked ) {
								$( this ).children().each( function( e ){
									oldbaseBodyBackground[ e ] = {
										background_color : $( this ).css( 'background-color' ), color : $( this ).css( 'color' )
									};
								} );
								$( this ).children().css( 'background-color', animatedBodyDarkShade );
								$( this ).children().css( 'color', animatedHeadForeColor );
								$( this ).removeClass( inActiveBaseBodyTdClass ).addClass( activeBaseBodyTdClass );
							}
						}, function(){
							if ( !baseAnimationBlocked && oldbaseBodyBackground.length > 0 ) {
								$( this ).children().each( function( e ){
									$( this ).css( {
										'background-color' : oldbaseBodyBackground[ e ].background_color, 'color' : oldbaseBodyBackground[ e ].color
									} );
								} );
								$( this ).removeClass( activeBaseBodyTdClass ).addClass( inActiveBaseBodyTdClass );
							}
						} );

						$( animateHeadTds ).hover( function(){
							var currIndex = $( this ).data( 'index' );
							window.__activeAnimElemIndex = currIndex;
							if ( !addOnAnimationBlocked ) {
								// refreshHeadColoring(currIndex);
								refreshBodyColoring( currIndex, window.__selectedAddonIndex );
							}
						}, function(){
							var currIndex = $( this ).data( 'index' );
							window.__inActiveAnimElemIndex = currIndex;

							if ( !addOnAnimationBlocked ) {
								// resetHeadColoring();
								resetBodyColoring( window.__selectedAddonIndex );
							}
						} );

						$( animateBodyTds ).hover( function(){
							var currIndex = $( this ).data( 'index' );
							window.__activeAnimElemIndex = currIndex;
							/*
							 * //Fadeout to give smooth feeling $(headAbsDivs).each(function (e) { if (e <= currIndex) $(this).fadeOut(300); });
							 */
							if ( !addOnAnimationBlocked ) {
								// refreshHeadColoring(currIndex);
								refreshBodyColoring( currIndex, window.__selectedAddonIndex );
							}
						}, function(){
							var currIndex = $( this ).data( 'index' );
							window.__inActiveAnimElemIndex = currIndex;
							/*
							 * //Show prev abs div if (currIndex > window.__activeAnimElemIndex) $(headAbsDivs[currIndex]).show(); //if all mouse is not on any td if (window.__activeAnimElemIndex === window.__inActiveAnimElemIndex) $(headAbsDivs).each(function () { $(this).show(); });
							 */

							if ( !addOnAnimationBlocked ) {
								// resetHeadColoring();
								resetBodyColoring( window.__selectedAddonIndex );
							}
						} );

						var refreshHeadColoring = function( activeindex ){
							$( animateHeadTds ).each( function( e ){
								// if (e <= activeindex) {
								// $(this).removeClass(inActiveHeadTdClass)
								// .addClass(activeHeadTdClass)
								// .css({
								// 'background-color': '#' + headShadeColors[e],
								// 'color': animatedHeadForeColor
								// });

								// } else {
								// $(this).removeClass(activeHeadTdClass)
								// .addClass(inActiveHeadTdClass)
								// .css({
								// 'background-color': __oldHeadClrs[e].background_color,
								// 'color': __oldHeadClrs[e].color
								// });
								// }
								if ( e == activeindex ) {
									$( this ).removeClass( inActiveHeadTdClass ).addClass( activeHeadTdClass ).css( {
										'background-color' : '#' + headShadeColors[ e ], 'color' : animatedHeadForeColor
									} );

								}
								else {
									$( this ).removeClass( activeHeadTdClass ).addClass( inActiveHeadTdClass ).css( {
										'background-color' : __oldHeadClrs[ e ].background_color, 'color' : __oldHeadClrs[ e ].color
									} );
								}
							} );
						};
						var refreshBodyColoring = function( activeindex, selectedAddonIndex ){
							if ( selectedAddonIndex >= 0 ) {
								$( animateBodyTds ).each( function( e ){
									if ( e <= selectedAddonIndex || e <= activeindex ) {
										$( this ).removeClass( inActiveBodyTdClass ).addClass( activeBodyTdClass ).css( {
											'background-color' : '#' + bodyShadeColors[ e ]
										} );

										$( '.topArrow[data-index=' + e + ']' ).removeClass( 'topArrowLight' ).addClass( 'topArrowDark' );
									}
									else {
										if ( activeindex < selectedAddonIndex ) {
											$( this ).removeClass( activeBodyTdClass ).addClass( inActiveBodyTdClass ).css( {
												'background-color' : __oldBodyClrs[ e ].background_color, 'color' : __oldBodyClrs[ e ].color
											} );

											$( '.topArrow[data-index=' + e + ']' ).removeClass( 'topArrowDark' ).addClass( 'topArrowLight' );
										}
									}
								} );
							}
							else {
								$( animateBodyTds ).each( function( e ){
									if ( e <= activeindex ) {
										$( this ).removeClass( inActiveBodyTdClass ).addClass( activeBodyTdClass ).css( {
											'background-color' : '#' + bodyShadeColors[ e ]
										} );

										$( '.topArrow[data-index=' + e + ']' ).removeClass( 'topArrowLight' ).addClass( 'topArrowDark' );
									}
									else {
										$( this ).removeClass( activeBodyTdClass ).addClass( inActiveBodyTdClass ).css( {
											'background-color' : __oldBodyClrs[ e ].background_color, 'color' : __oldBodyClrs[ e ].color
										} );

										$( '.topArrow[data-index=' + e + ']' ).removeClass( 'topArrowDark' ).addClass( 'topArrowLight' );
									}
								} );

							}
						};
						var resetHeadColoring = function(){
							$( animateHeadTds ).removeClass( activeHeadTdClass ).addClass( inActiveHeadTdClass ).each( function( e ){
								$( this ).css( {
									'background-color' : __oldHeadClrs[ e ].background_color, 'color' : __oldHeadClrs[ e ].color
								} );
							} );
						};
						var resetBodyColoring = function( selectedAddonIndex ){
							if ( selectedAddonIndex >= 0 ) {
								$( animateBodyTds ).each( function( e ){
									if ( e > selectedAddonIndex ) {
										$( this ).removeClass( activeBodyTdClass ).addClass( inActiveBodyTdClass ).css( {
											'background-color' : __oldBodyClrs[ e ].background_color, 'color' : __oldBodyClrs[ e ].color
										} );

										$( '.topArrow[data-index=' + e + ']' ).removeClass( 'topArrowDark' ).addClass( 'topArrowLight' );
									}
								} );
							}
							else {
								$( animateBodyTds ).removeClass( activeBodyTdClass ).addClass( inActiveBodyTdClass ).each( function( e ){
									$( this ).css( {
										'background-color' : __oldBodyClrs[ e ].background_color, 'color' : __oldBodyClrs[ e ].color
									} );
								} );

								$( '.topArrow' ).removeClass( 'topArrowDark' ).addClass( 'topArrowLight' );
							}
						};

						$( '.myopContainer .myopBaseplan, .myopContainer .myopAddonplan' )
								.tooltip(
										{
											track : true,
											delay : 0,
											showURL : false,
											showBody : false,
											opacity : 1,
											fixPNG : true,
											top : 20,
											left : 10,
											bodyHandler : function(){
												var summarryHtml = $( '    <div class="summaryContainer">'
														+ '        <span class="sumarryHeading">Summary: </span>'
														+ '        <p><span class="sumarryLabel">Base Plan:</span><span class="sumarryData" id="sumarryBasePlanPrice"></span></p>'
														+ '        <p><span class="sumarryLabel">Add-on Plan:</span><span class="sumarryData" id="sumarryAddonPlanPrice"></span></p>'
														+ '        <p><span class="sumarryLabel">Total:</span><span class="sumarryData" id="sumarryTotalPlanPrice"></span></p>'
														+ '    </div>' );
												var sumarry = calculateSummary();
												$( summarryHtml ).find( '#sumarryBasePlanPrice' ).text( sumarry.BasePrice );
												$( summarryHtml ).find( '#sumarryAddonPlanPrice' ).text( sumarry.AddOnPrice );
												$( summarryHtml ).find( '#sumarryTotalPlanPrice' ).text( sumarry.TotalPrice );
												return summarryHtml;
											}
										} );

						var calculateSummary = function(){
							var _result = {
								BasePrice : 0, AddOnPrice : 0, TotalPrice : 0
							};
							var _basePrice = $( '.activeBaseBodyTd span[data-price]' ).data( 'price' );
							var _addonPrice = 0;
							$( '.activeBodyTd span[data-price]' ).each( function(){
								_addonPrice += $( this ).data( 'price' );
							} );

							if ( _basePrice || _addonPrice ) {
								_result.BasePrice = _basePrice | 0;
								_result.AddOnPrice = _addonPrice | 0;
								_result.TotalPrice = _basePrice + _addonPrice;
							}
							return _result;
						};

					} );

} )( jQuery );

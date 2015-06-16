//package com.project.tripmania;
//
//import java.security.Permissions;
//import java.util.List;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.util.Log;
//import android.util.Pair;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.facebook.widget.FacebookDialog;
//import com.google.analytics.tracking.android.EasyTracker;
//import com.sromku.simple.fb.Permission;
//import com.sromku.simple.fb.SimpleFacebook;
//import com.sromku.simple.fb.entities.Album;
//import com.sromku.simple.fb.entities.Checkin;
//import com.sromku.simple.fb.entities.Comment;
//import com.sromku.simple.fb.entities.Event;
//import com.sromku.simple.fb.entities.Event.EventDecision;
//import com.sromku.simple.fb.entities.Feed;
//import com.sromku.simple.fb.entities.Group;
//import com.sromku.simple.fb.entities.Like;
//import com.sromku.simple.fb.entities.Page;
//import com.sromku.simple.fb.entities.Photo;
//import com.sromku.simple.fb.entities.Post;
//import com.sromku.simple.fb.entities.Privacy;
//import com.sromku.simple.fb.entities.Privacy.PrivacySettings;
//import com.sromku.simple.fb.entities.Profile;
//import com.sromku.simple.fb.entities.Video;
//import com.sromku.simple.fb.example.utils.Utils;
//import com.sromku.simple.fb.listeners.OnAlbumsListener;
//import com.sromku.simple.fb.listeners.OnCheckinsListener;
//import com.sromku.simple.fb.listeners.OnCommentsListener;
//import com.sromku.simple.fb.listeners.OnEventsListener;
//import com.sromku.simple.fb.listeners.OnFriendsListener;
//import com.sromku.simple.fb.listeners.OnGroupsListener;
//import com.sromku.simple.fb.listeners.OnInviteListener;
//import com.sromku.simple.fb.listeners.OnLikesListener;
//import com.sromku.simple.fb.listeners.OnLoginListener;
//import com.sromku.simple.fb.listeners.OnLogoutListener;
//import com.sromku.simple.fb.listeners.OnPageListener;
//import com.sromku.simple.fb.listeners.OnPhotosListener;
//import com.sromku.simple.fb.listeners.OnPostsListener;
//import com.sromku.simple.fb.listeners.OnProfileListener;
//import com.sromku.simple.fb.listeners.OnPublishListener;
//import com.sromku.simple.fb.listeners.OnVideosListener;
//import com.sromku.simple.fb.utils.Attributes;
//import com.sromku.simple.fb.utils.PictureAttributes;
//import com.sromku.simple.fb.utils.PictureAttributes.PictureType;
//
//public class Datagetter extends Activity {
//	protected static final String TAG = Datagetter.class.getName();
//	Intent intent ;
//	double lat, lng;
//	
//	private SimpleFacebook mSimpleFacebook;
//	DB db = new DB(this);
//	 String post_Id;
//	private ProgressDialog mProgress;
//	private Button mButtonLogin;
//	private Button mButtonLogout;
//	private TextView mTextStatus;
//	private Button mButtonPublishFeed;
//	private Button mButtonPublishStory;
//	private Button mButtonPublishPhoto;
//	private Button mButtonInviteAll;
//	private Button mButtonInviteSuggested;
//	private Button mButtonInviteOne;
//	private Button mButtonGetProfile;
//	private Button mButtonGetProfileProperties;
//	private Button mButtonGetFriends;
//	private Button mButtonGetAlbums;
//	private Button mButtonFragments;
//
//	// new stuff
//	private Button mButtonGetCheckins;
//	private Button mButtonGetComments;
//	private Button mButtonGetEvents;
//	private Button mButtonGetGroups;
//	private Button mButtonGetLikes;
//	private Button mButtonGetPhotos;
//	private Button mButtonGetPosts;
//	private Button mButtonGetVideos;
//	private Button mButtonGetPage;
//
//	// Login listener
//	private OnLoginListener mOnLoginListener = new OnLoginListener() { 
//
//		@Override
//		public void onFail(String reason) {
////			mTextStatus.setText(reason);
//			Log.w(TAG, "Failed to login");
//		}
//
//		@Override
//		public void onException(Throwable throwable) {
////			mTextStatus.setText("Exception: " + throwable.getMessage());
//			Log.e(TAG, "Bad thing happened", throwable);
//		}
//
//		@Override
//		public void onThinking() {
//			// show progress bar or something to the user while login is
//			// happening
////			mTextStatus.setText("Thinking...");
//		}
//
//		@Override
//		public void onLogin() {
//			// change the state of the button or do whatever you want
////			mTextStatus.setText("Logged in");
////			loggedInUIState();
//			toast("Logging in to your profile !!");
//		}
//
//		@Override
//		public void onNotAcceptingPermissions(Permission.Type type) {
//			toast(String.format("You didn't accept %s permissions", type.name()));
//		}
//	};
//
//	// Logout listener
//	private OnLogoutListener mOnLogoutListener = new OnLogoutListener() {
//
//		@Override
//		public void onFail(String reason) {
////			mTextStatus.setText(reason);
//			Log.w(TAG, "Failed to login");
//		}
//
//		@Override
//		public void onException(Throwable throwable) {
////			mTextStatus.setText("Exception: " + throwable.getMessage());
//			Log.e(TAG, "Bad thing happened", throwable);
//		}
//
//		@Override
//		public void onThinking() {
//			// show progress bar or something to the user while login is
//			// happening
////			mTextStatus.setText("Thinking...");
//		}
//
//		@Override
//		public void onLogout() {
//			// change the state of the button or do whatever you want
////			mTextStatus.setText("Logged out");
////			loggedOutUIState();
//			toast("Logging Out !! ");
//		}
//
//	};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		intent = getIntent();
//		lat = intent.getDoubleExtra("lat", 0);
//		lng = intent.getDoubleExtra("lng", 0);
//		String action = intent.getStringExtra("action");
//		mSimpleFacebook = SimpleFacebook.getInstance(this);
//		// test local language
//		Utils.updateLanguage(getApplicationContext(), "en");
//		Utils.printHashKey(getApplicationContext());
//
//		//setContentView(R.layout.activity_blank);
//		initUI();
//		loginExample();
//		if(action.contentEquals("post_fb"))
//			publishFeedExample();
//		else
//			getCommentsExample();
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		mSimpleFacebook = SimpleFacebook.getInstance(this);
//		setUIState();
//	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data);
//	}
//
//	/**
//	 * Login example.
//	 */
//	private void loginExample() {
////		mButtonLogin.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View arg0) {
//				mSimpleFacebook.login(mOnLoginListener);
////			}
////		});
//	}
//
//	/**
//	 * Logout example
//	 */
//	private void logoutExample() {
////		mButtonLogout.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View arg0) {
//				mSimpleFacebook.logout(mOnLogoutListener);
////			}
////		});
//	}
//
//	/**
//	 * Publish feed example. <br>
//	 * Must use {@link Permissions#PUBLISH_ACTION}
//	 */
//	private void publishFeedExample() {
//		// listener for publishing action
//		final OnPublishListener onPublishListener = new OnPublishListener() {
//
//			
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				// insure that you are logged in before publishing
//				Log.w(TAG, "Failed to publish on your wall , Try sometime Later ");
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				toast("SOME ERROR OCCURED ");
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				// show progress bar or something to the user while publishing
//				showDialog();
//			}
//
//			@Override
//			public void onComplete(String postId) {
//				hideDialog();
//				//postId
//				// post ID yeh hai is se comments pkrne hain..
//				//post_Id = postId;
//				toast("Your Location is Sucessfully Posted on Your Wall Publicly !!!");
//				
//			}
//		};
//		
////		FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
////        .setLink("http://maps.google.com/?q=" + lat + "," + lng)
////        .build();
//		//.setMessage("I am at " + intent.getStringExtra("location"))
//		// feed builder
//		final Feed feed = new Feed.Builder()
//				.setLink("http://maps.google.com/?q=" + lat + "," + lng)
//				.setName("Tripmania Locator")
//				.setCaption("My Current Location ")
//				.setDescription("Tripmania offers its users to search resturants and places in their nearby places by either using their present location or searching another place ANYWHERE in the World !!")
//				.build();
////				.setPicture("https://www.facebook.com/photo.php?fbid=854451537901584&set=a.608450832501657.150769.100000102378548&type=1&theater")
//				
//				//.setPicture("https://raw.github.com/sromku/android-simple-facebook/master/Refs/android_facebook_sdk_logo.png").setLink("https://github.com/sromku/android-simple-facebook").build();
//
//		// click on button and publish
////		mButtonPublishFeed.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View v) {
//				mSimpleFacebook.publish(feed, false, onPublishListener);
//				
////			}
////		});
//	}
//
//	/**
//	 * Publish story (open graph) example
//	 */
//	private void publishStoryExample() {
//	}
//
//	/**
//	 * Publish photo example. <br>
//	 * Must use {@link Permissions#PUBLISH_STREAM}
//	 */
//	private void publishPhotoExample() {
//		mButtonPublishPhoto.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// take screenshot
//				final Bitmap bitmap = Utils.takeScreenshot(Datagetter.this);
//
//				// set privacy
//				Privacy privacy = new Privacy.Builder()
//						.setPrivacySettings(PrivacySettings.SELF)
//						.build();
//				
//				
//				// create Photo instance and add some properties
//				Photo photo = new Photo.Builder()
//						.setImage(bitmap)
//						.setName("Screenshot from #android_simple_facebook sample application")
//						.setPlace("110619208966868")
//						.setPrivacy(privacy)
//						.build();
//
//				// publish
//				mSimpleFacebook.publish(photo, new OnPublishListener() {
//
//					@Override
//					public void onFail(String reason) {
//						hideDialog();
//						// insure that you are logged in before publishing
//						Log.w(TAG, "Failed to publish");
//						toast(reason);
//					}
//
//					@Override
//					public void onException(Throwable throwable) {
//						hideDialog();
//						Log.e(TAG, "Bad thing happened", throwable);
//					}
//
//					@Override
//					public void onThinking() {
//						// show progress bar or something to the user while
//						// publishing
//						showDialog();
//					}
//
//					@Override
//					public void onComplete(String id) {
//						hideDialog();
//						toast("Published successfully. The new image id = " + id);
//					}
//				});
//			}
//		});
//	}
//
//	/**
//	 * Invite all, several, one friend/s examples
//	 */
//	private void inviteExamples() {
//		// listener for invite action
//		final OnInviteListener onInviteListener = new OnInviteListener() {
//
//			@Override
//			public void onFail(String reason) {
//				// insure that you are logged in before inviting
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onComplete(List<String> invitedFriends, String requestId) {
//				toast("Invitation was sent to " + invitedFriends.size() + " users, invite request=" + requestId);
//			}
//
//			@Override
//			public void onCancel() {
//				toast("Canceled the dialog");
//			}
//
//		};
//
//		// invite all
//		mButtonInviteAll.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// will open dialog with all my friends
//				mSimpleFacebook.invite("I invite you to use this app", onInviteListener, "secret data");
//			}
//		});
//
//		// invite suggested
//		mButtonInviteSuggested.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				String[] friends = new String[] { "630243197", "584419361", "1456233371", "100000490891462" };
//				mSimpleFacebook.invite(friends, "I invite you to use this app", onInviteListener, "secret data");
//			}
//		});
//
//		// invite one
//		mButtonInviteOne.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				String friend = "630243197";
//				mSimpleFacebook.invite(friend, "I invite you to use this app", onInviteListener, "secret data");
//			}
//		});
//	}
//
//	/**
//	 * Get my profile example
//	 */
//	private void getProfileExample() {
//		// listener for profile request
//		final OnProfileListener onProfileListener = new OnProfileListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				// insure that you are logged in before getting the profile
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				// show progress bar or something to the user while fetching
//				// profile
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(Profile profile) {
//				hideDialog();
//				Log.i(TAG, "My profile id = " + profile.getId());
//				String name = profile.getName();
//				String email = profile.getEmail();
//				toast("name = " + name + ", email = " + String.valueOf(email));
//			}
//		};
//
//		// set on click button
//		mButtonGetProfile.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// example 1
//				mSimpleFacebook.getProfile(onProfileListener);
//
//				// // - example 2
//				// mSimpleFacebook.getProfile(new OnProfileRequestAdapter()
//				// {
//				// @Override
//				// public void onComplete(Profile profile)
//				// {
//				// String id = profile.getId();
//				// String firstName = profile.getFirstName();
//				// String birthday = profile.getBirthday();
//				// String email = profile.getEmail();
//				// String bio = profile.getBio();
//				// // ... and many more properties of profile ...
//				// }
//				//
//				// });
//			}
//		});
//
//	}
//
//	/**
//	 * Get my profile with properties example
//	 */
//	private void getProfileWithPropertiesExample() {
//		// listener for profile request
//		final OnProfileListener onProfileListener = new OnProfileListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				// insure that you are logged in before getting the profile
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				// show progress bar or something to the user while fetching
//				// profile
//				Log.i(TAG, "Thinking...");
//			}
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public void onComplete(Profile profile) {
//				hideDialog();
//				String id = profile.getId();
//				String firstName = profile.getFirstName();
//				Photo photo = profile.getCover();
//				String pictureUrl = profile.getPicture();
//
//				// this is just to show the results
//				AlertDialog dialog = Utils.buildProfileResultDialog(Datagetter.this, new Pair<String, String>(Profile.Properties.ID, id), new Pair<String, String>(Profile.Properties.FIRST_NAME,
//						firstName), new Pair<String, String>(Profile.Properties.COVER, photo.getSource()), new Pair<String, String>(Profile.Properties.PICTURE, pictureUrl));
//				dialog.show();
//			}
//		};
//
//		// set on click button
//		mButtonGetProfileProperties.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// prepare specific picture that we need
//				PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
//				pictureAttributes.setHeight(500);
//				pictureAttributes.setWidth(500);
//				pictureAttributes.setType(PictureType.SQUARE);
//
//				// prepare the properties that we need
//				Profile.Properties properties = new Profile.Properties.Builder().add(Profile.Properties.ID).add(Profile.Properties.FIRST_NAME).add(Profile.Properties.COVER)
//						.add(Profile.Properties.PICTURE, pictureAttributes).build();
//
//				// do the get profile action
//				mSimpleFacebook.getProfile(properties, onProfileListener);
//			}
//		});
//
//	}
//
//	/**
//	 * Get friends example
//	 */
//	private void getFriendsExample() {
//		// listener for friends request
//		final OnFriendsListener onFriendsListener = new OnFriendsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				// insure that you are logged in before getting the friends
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				// show progress bar or something to the user while fetching
//				// profile
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Profile> friends) {
//				hideDialog();
//				Log.i(TAG, "Number of friends = " + friends.size());
//				toast("Number of friends = " + friends.size());
//			}
//
//		};
//
//		// set button
//		mButtonGetFriends.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getFriends(onFriendsListener);
//			}
//		});
//
//	}
//
//	/**
//	 * Get albums example <br>
//	 * Must use {@link Permissions#USER_PHOTOS}
//	 */
//	private void getAlbumsExample() {
//		// listener for friends request
//		final OnAlbumsListener onAlbumsListener = new OnAlbumsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Album> albums) {
//				hideDialog();
//				Log.i(TAG, "Number of albums = " + albums.size());
//				toast("Number of albums = " + albums.size());
//				if (hasNext()) {
//					toast("Has more albums");
//				}
//			}
//		};
//
//		// set button
//		mButtonGetAlbums.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getAlbums(onAlbumsListener);
//			}
//		});
//
//	}
//
//	private void getCheckinsExample() {
//		final OnCheckinsListener onCheckinsListener = new OnCheckinsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Checkin> response) {
//				hideDialog();
//				Log.i(TAG, "Number of checkins = " + response.size());
//				toast("Number of checkins = " + response.size());
//			}
//		};
//
//		mButtonGetCheckins.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getCheckins(onCheckinsListener);
//			}
//		});
//	}
//
//	private void getCommentsExample() {
//		
//		int x = db.getLastPostID();
//		final String entityId =  String.valueOf(db.getLastPostID());
//		final OnCommentsListener onCommentsListener = new OnCommentsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Comment> response) {
//				hideDialog();
//				Log.i(TAG, "Number of comments = " + response.size());
//				toast("Number of comments = " + response.size());
//			}
//		};
//
//		mButtonGetComments.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getComments(entityId, onCommentsListener);
//			}
//		});
//	}
//
//	private void getEventsExample() {
//		final EventDecision eventDesicion = EventDecision.ATTENDING;
//		final OnEventsListener onEventsListener = new OnEventsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Event> response) {
//				hideDialog();
//				Log.i(TAG, "Number of events = " + response.size());
//				toast("Number of events = " + response.size());
//			}
//		};
//
//		mButtonGetEvents.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getEvents(eventDesicion, onEventsListener);
//			}
//		});
//	}
//
//	private void getGroupsExample() {
//		final OnGroupsListener onGroupsListener = new OnGroupsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Group> response) {
//				hideDialog();
//				Log.i(TAG, "Number of groups = " + response.size());
//				toast("Number of groups = " + response.size());
//			}
//		};
//
//		mButtonGetGroups.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getGroups(onGroupsListener);
//			}
//		});
//	}
//
//	private void getLikesExample() {
//		final String entityId = "14104316802_522484207864952";
//		final OnLikesListener onLikesListener = new OnLikesListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Like> response) {
//				hideDialog();
//				Log.i(TAG, "Number of likes = " + response.size());
//				toast("Number of likes = " + response.size());
//			}
//		};
//
//		mButtonGetLikes.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getLikes(entityId, onLikesListener);
//			}
//		});
//	}
//
//	private void getPhotosExample() {
//
//		final OnPhotosListener onPhotosListener = new OnPhotosListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Photo> response) {
//				hideDialog();
//				Log.i(TAG, "Number of photos = " + response.size());
//				toast("First photo id = " + response.get(0).getId());
//				if (hasNext()) {
//					toast("Has more photos");
//					// getNext();
//				}
//			}
//		};
//
//		mButtonGetPhotos.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getPhotos(onPhotosListener);
//			}
//		});
//	}
//
//	private void getPostsExample() {
//		final OnPostsListener onPostsListener = new OnPostsListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Post> response) {
//				hideDialog();
//				Log.i(TAG, "Number of posts = " + response.size());
//				toast("Number of posts = " + response.size());
//			}
//		};
//
//		mButtonGetPosts.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getPosts(onPostsListener);
//			}
//		});
//	}
//
//	private void getVideosExample() {
//		final OnVideosListener onVideosListener = new OnVideosListener() {
//
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(List<Video> response) {
//				hideDialog();
//				Log.i(TAG, "Number of videos = " + response.size());
//				toast("Number of videos = " + response.size());
//			}
//		};
//
//		mButtonGetVideos.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getVideos(onVideosListener);
//			}
//		});
//	}
//
//	private void getPage() {
//		final String pageId = "109984829031269";
//		final Page.Properties properties = new Page.Properties.Builder()
//			.add(Page.Properties.NAME)
//			.add(Page.Properties.DESCRIPTION)
//			.build();
//		
//		final OnPageListener onPageListener = new OnPageListener() {
//			@Override
//			public void onFail(String reason) {
//				hideDialog();
//				Log.w(TAG, reason);
//			}
//
//			@Override
//			public void onException(Throwable throwable) {
//				hideDialog();
//				Log.e(TAG, "Bad thing happened", throwable);
//			}
//
//			@Override
//			public void onThinking() {
//				showDialog();
//				Log.i(TAG, "Thinking...");
//			}
//
//			@Override
//			public void onComplete(Page response) {
//				hideDialog();
//				Log.i(TAG, "Page name = " + response.getName());
//				toast("Page name = " + response.getName());
//			}
//		};
//		
//		mButtonGetPage.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mSimpleFacebook.getPage(pageId, properties, onPageListener);
//			}
//		});
//		
//	}
//	
//	private void initUI() {
//		mButtonLogin = (Button) findViewById(R.id.button_login);
//		mButtonLogout = (Button) findViewById(R.id.button_logout);
//		mTextStatus = (TextView) findViewById(R.id.text_status);
//		mButtonPublishFeed = (Button) findViewById(R.id.button_publish_feed);
//	
//		mButtonPublishPhoto = (Button) findViewById(R.id.button_publish_photo);
//	
//		mButtonGetProfile = (Button) findViewById(R.id.button_get_profile);
//		mButtonGetProfileProperties = (Button) findViewById(R.id.button_get_profile_with_properties);
//	
//		mButtonGetComments = (Button) findViewById(R.id.button_get_comments);
//	
//		mButtonGetLikes = (Button) findViewById(R.id.button_get_likes);
//	
//		mButtonGetPosts = (Button) findViewById(R.id.button_get_posts);
//		
//	
//	}
//
//	private void setUIState() {
//		if (mSimpleFacebook.isLogin()) {
//			loggedInUIState();
//		}
//		else {
//			loggedOutUIState();
//		}
//	}
//
//	/**
//	 * Show toast
//	 * 
//	 * @param message
//	 */
//	private void toast(String message) {
//		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//	}
//
//	private void loggedInUIState() {
////		mButtonLogin.setEnabled(false);
////		mButtonLogout.setEnabled(true);
////		mButtonPublishFeed.setEnabled(true);
////	
////		mButtonPublishPhoto.setEnabled(true);
////	
////		mButtonGetProfile.setEnabled(true);
////		mButtonGetProfileProperties.setEnabled(true);
////	
////
////		mButtonGetComments.setEnabled(true);
////
////		mButtonGetLikes.setEnabled(true);
////
////		mButtonGetPosts.setEnabled(true);
//
//
////		mTextStatus.setText("Logged in");
//	}
//
//	private void loggedOutUIState() {
////		mButtonLogin.setEnabled(true);
////		mButtonLogout.setEnabled(false);
////		mButtonPublishFeed.setEnabled(false);
////		
////		mButtonPublishPhoto.setEnabled(false);
////		
////		mButtonGetProfile.setEnabled(false);
////		mButtonGetProfileProperties.setEnabled(false);
////		
////
////		
////		mButtonGetComments.setEnabled(false);
////		
////		mButtonGetLikes.setEnabled(false);
////		mButtonGetPosts.setEnabled(false);
//
//
////		mTextStatus.setText("Logged out");
//	}
//
//	private void showDialog() {
//		mProgress = ProgressDialog.show(this, "Posting On facebook ", "Waiting for Facebook To Post on Your Wall", true);
//	}
//
//	private void hideDialog() {
//		if (mProgress != null) {
//			mProgress.hide();
//		}
//	}
//	
//	@Override
//	  public void onStart() {
//	    super.onStart();
//	    // The rest of your onStart() code.
//	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
//	  }
//
//	  @Override
//	  public void onStop() {
//	    super.onStop();
//	    // The rest of your onStop() code.
//	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
//	  }
//
//}

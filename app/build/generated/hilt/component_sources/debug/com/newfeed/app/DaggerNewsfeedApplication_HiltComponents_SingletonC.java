package com.newfeed.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.newfeed.app.data.local.DatabaseInitializer;
import com.newfeed.app.data.local.NewsFeedDatabase;
import com.newfeed.app.data.local.dao.CommentDao;
import com.newfeed.app.data.local.dao.LikeDao;
import com.newfeed.app.data.local.dao.PostDao;
import com.newfeed.app.data.local.dao.UserDao;
import com.newfeed.app.data.repository.CommentRepositoryImpl;
import com.newfeed.app.data.repository.PostRepositoryImpl;
import com.newfeed.app.data.repository.UserRepositoryImpl;
import com.newfeed.app.di.AppModule_ProvideApplicationScopeFactory;
import com.newfeed.app.di.DatabaseModule_ProvideCommentDaoFactory;
import com.newfeed.app.di.DatabaseModule_ProvideLikeDaoFactory;
import com.newfeed.app.di.DatabaseModule_ProvideNewfeedDatabaseFactory;
import com.newfeed.app.di.DatabaseModule_ProvidePostDaoFactory;
import com.newfeed.app.di.DatabaseModule_ProvideUserDaoFactory;
import com.newfeed.app.domain.repository.CommentRepository;
import com.newfeed.app.domain.repository.PostRepository;
import com.newfeed.app.domain.repository.UserRepository;
import com.newfeed.app.domain.usecase.CreateCommentUseCase;
import com.newfeed.app.domain.usecase.CreatePostUseCase;
import com.newfeed.app.domain.usecase.GetAllPostsUseCase;
import com.newfeed.app.domain.usecase.GetCommentsUseCase;
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase;
import com.newfeed.app.domain.usecase.ToggleLikeUseCase;
import com.newfeed.app.presentation.MainActivity;
import com.newfeed.app.presentation.comments.CommentsViewModel;
import com.newfeed.app.presentation.comments.CommentsViewModel_HiltModules;
import com.newfeed.app.presentation.comments.CommentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.newfeed.app.presentation.comments.CommentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.newfeed.app.presentation.createpost.CreatePostViewModel;
import com.newfeed.app.presentation.createpost.CreatePostViewModel_HiltModules;
import com.newfeed.app.presentation.createpost.CreatePostViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.newfeed.app.presentation.createpost.CreatePostViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.newfeed.app.presentation.newsfeed.NewsFeedViewModel;
import com.newfeed.app.presentation.newsfeed.NewsFeedViewModel_HiltModules;
import com.newfeed.app.presentation.newsfeed.NewsFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.newfeed.app.presentation.newsfeed.NewsFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.newfeed.app.presentation.profile.ProfileViewModel;
import com.newfeed.app.presentation.profile.ProfileViewModel_HiltModules;
import com.newfeed.app.presentation.profile.ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.newfeed.app.presentation.profile.ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DaggerNewsfeedApplication_HiltComponents_SingletonC {
  private DaggerNewsfeedApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public NewsfeedApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements NewsfeedApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements NewsfeedApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements NewsfeedApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements NewsfeedApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements NewsfeedApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements NewsfeedApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements NewsfeedApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public NewsfeedApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends NewsfeedApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends NewsfeedApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends NewsfeedApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends NewsfeedApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(4).put(CommentsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CommentsViewModel_HiltModules.KeyModule.provide()).put(CreatePostViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CreatePostViewModel_HiltModules.KeyModule.provide()).put(NewsFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, NewsFeedViewModel_HiltModules.KeyModule.provide()).put(ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProfileViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends NewsfeedApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CommentsViewModel> commentsViewModelProvider;

    private Provider<CreatePostViewModel> createPostViewModelProvider;

    private Provider<NewsFeedViewModel> newsFeedViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetCommentsUseCase getCommentsUseCase() {
      return new GetCommentsUseCase(singletonCImpl.bindCommentRepositoryProvider.get());
    }

    private CreateCommentUseCase createCommentUseCase() {
      return new CreateCommentUseCase(singletonCImpl.bindCommentRepositoryProvider.get());
    }

    private GetCurrentUserUseCase getCurrentUserUseCase() {
      return new GetCurrentUserUseCase(singletonCImpl.bindUserRepositoryProvider.get());
    }

    private CreatePostUseCase createPostUseCase() {
      return new CreatePostUseCase(singletonCImpl.bindPostRepositoryProvider.get());
    }

    private GetAllPostsUseCase getAllPostsUseCase() {
      return new GetAllPostsUseCase(singletonCImpl.bindPostRepositoryProvider.get());
    }

    private ToggleLikeUseCase toggleLikeUseCase() {
      return new ToggleLikeUseCase(singletonCImpl.bindPostRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.commentsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.createPostViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.newsFeedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(4).put(CommentsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) commentsViewModelProvider)).put(CreatePostViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) createPostViewModelProvider)).put(NewsFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) newsFeedViewModelProvider)).put(ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) profileViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.newfeed.app.presentation.comments.CommentsViewModel 
          return (T) new CommentsViewModel(viewModelCImpl.savedStateHandle, viewModelCImpl.getCommentsUseCase(), viewModelCImpl.createCommentUseCase(), viewModelCImpl.getCurrentUserUseCase());

          case 1: // com.newfeed.app.presentation.createpost.CreatePostViewModel 
          return (T) new CreatePostViewModel(viewModelCImpl.createPostUseCase(), viewModelCImpl.getCurrentUserUseCase());

          case 2: // com.newfeed.app.presentation.newsfeed.NewsFeedViewModel 
          return (T) new NewsFeedViewModel(viewModelCImpl.getAllPostsUseCase(), viewModelCImpl.toggleLikeUseCase(), viewModelCImpl.getCurrentUserUseCase());

          case 3: // com.newfeed.app.presentation.profile.ProfileViewModel 
          return (T) new ProfileViewModel(viewModelCImpl.getCurrentUserUseCase(), viewModelCImpl.getAllPostsUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends NewsfeedApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends NewsfeedApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends NewsfeedApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<NewsFeedDatabase> provideNewfeedDatabaseProvider;

    private Provider<DatabaseInitializer> databaseInitializerProvider;

    private Provider<CoroutineScope> provideApplicationScopeProvider;

    private Provider<CommentRepositoryImpl> commentRepositoryImplProvider;

    private Provider<CommentRepository> bindCommentRepositoryProvider;

    private Provider<UserRepositoryImpl> userRepositoryImplProvider;

    private Provider<UserRepository> bindUserRepositoryProvider;

    private Provider<PostRepositoryImpl> postRepositoryImplProvider;

    private Provider<PostRepository> bindPostRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private UserDao userDao() {
      return DatabaseModule_ProvideUserDaoFactory.provideUserDao(provideNewfeedDatabaseProvider.get());
    }

    private PostDao postDao() {
      return DatabaseModule_ProvidePostDaoFactory.providePostDao(provideNewfeedDatabaseProvider.get());
    }

    private CommentDao commentDao() {
      return DatabaseModule_ProvideCommentDaoFactory.provideCommentDao(provideNewfeedDatabaseProvider.get());
    }

    private LikeDao likeDao() {
      return DatabaseModule_ProvideLikeDaoFactory.provideLikeDao(provideNewfeedDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideNewfeedDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<NewsFeedDatabase>(singletonCImpl, 1));
      this.databaseInitializerProvider = DoubleCheck.provider(new SwitchingProvider<DatabaseInitializer>(singletonCImpl, 0));
      this.provideApplicationScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonCImpl, 2));
      this.commentRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 3);
      this.bindCommentRepositoryProvider = DoubleCheck.provider((Provider) commentRepositoryImplProvider);
      this.userRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 4);
      this.bindUserRepositoryProvider = DoubleCheck.provider((Provider) userRepositoryImplProvider);
      this.postRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 5);
      this.bindPostRepositoryProvider = DoubleCheck.provider((Provider) postRepositoryImplProvider);
    }

    @Override
    public void injectNewsfeedApplication(NewsfeedApplication newsfeedApplication) {
      injectNewsfeedApplication2(newsfeedApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private NewsfeedApplication injectNewsfeedApplication2(NewsfeedApplication instance) {
      NewsfeedApplication_MembersInjector.injectDatabaseInitializer(instance, databaseInitializerProvider.get());
      NewsfeedApplication_MembersInjector.injectApplicationScope(instance, provideApplicationScopeProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.newfeed.app.data.local.DatabaseInitializer 
          return (T) new DatabaseInitializer(singletonCImpl.userDao(), singletonCImpl.postDao());

          case 1: // com.newfeed.app.data.local.NewsFeedDatabase 
          return (T) DatabaseModule_ProvideNewfeedDatabaseFactory.provideNewfeedDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // kotlinx.coroutines.CoroutineScope 
          return (T) AppModule_ProvideApplicationScopeFactory.provideApplicationScope();

          case 3: // com.newfeed.app.data.repository.CommentRepositoryImpl 
          return (T) new CommentRepositoryImpl(singletonCImpl.commentDao(), singletonCImpl.postDao());

          case 4: // com.newfeed.app.data.repository.UserRepositoryImpl 
          return (T) new UserRepositoryImpl(singletonCImpl.userDao());

          case 5: // com.newfeed.app.data.repository.PostRepositoryImpl 
          return (T) new PostRepositoryImpl(singletonCImpl.postDao(), singletonCImpl.likeDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}

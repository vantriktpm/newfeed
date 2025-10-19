package com.newfeed.app.domain.usecase;

import com.newfeed.app.domain.repository.PostRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
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
public final class GetAllPostsUseCase_Factory implements Factory<GetAllPostsUseCase> {
  private final Provider<PostRepository> postRepositoryProvider;

  public GetAllPostsUseCase_Factory(Provider<PostRepository> postRepositoryProvider) {
    this.postRepositoryProvider = postRepositoryProvider;
  }

  @Override
  public GetAllPostsUseCase get() {
    return newInstance(postRepositoryProvider.get());
  }

  public static GetAllPostsUseCase_Factory create(
      javax.inject.Provider<PostRepository> postRepositoryProvider) {
    return new GetAllPostsUseCase_Factory(Providers.asDaggerProvider(postRepositoryProvider));
  }

  public static GetAllPostsUseCase_Factory create(Provider<PostRepository> postRepositoryProvider) {
    return new GetAllPostsUseCase_Factory(postRepositoryProvider);
  }

  public static GetAllPostsUseCase newInstance(PostRepository postRepository) {
    return new GetAllPostsUseCase(postRepository);
  }
}

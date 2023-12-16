//
//  OnboardingView.swift
//  iosApp
//
//  Created by Jan Janovec on 16.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct OnboardingView: View {
    @StateObject private var viewModel: OnboardingVM = .init()
    @EnvironmentObject private var settings: GlobalSettingsVM
    @State private var skipVisible = false
    var body: some View {
        ZStack {
            LinearGradient(colors: [.black, .orange], startPoint: .bottomLeading, endPoint: .topTrailing)
                .ignoresSafeArea()
            VStack(alignment: .leading, spacing: 16) {
                HStack {
                    Spacer()
                    Button {
                        viewModel.skip()
                    } label: {
                        HStack(spacing: 4) {
                            Text("Skip onboarding")
                            Image(systemName: "chevron.right")
                        }
                        .foregroundStyle(.ultraThickMaterial)
                    }
                }
                .opacity((skipVisible && viewModel.state.onboardingStep != .readyToSocialize) ? 1 : 0)
                .padding(.horizontal, 16)
                Spacer()
                Spacer()
                TabView(selection: $viewModel.state.onboardingStep) {
                    ForEach(OnboardingContractOnboardingStep.companion.items, id: \.stepNumber) { item in
                        OnboardingPage(item: item)
                            .tag(item)
                    }
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
                VStack {
                    RoundedRectangle(cornerRadius: 4)
                        .frame(height: 2)
                        .foregroundStyle(.ultraThickMaterial)
                    RoundedButton(text: viewModel.state.onboardingStep == .readyToSocialize ? "Finish" : "Next", foreground: Color.white, background: Color.orange) {
                        if viewModel.state.onboardingStep == .readyToSocialize {
                            settings.finishOnboarding()
                        } else {
                            viewModel.next()
                        }
                    }
                }
                .padding(.horizontal, 16)
            }
            .padding(.vertical, 16)
        }
        .onAppear {
            withAnimation {
                skipVisible = true
            }
        }
        .animation(.default, value: viewModel.state.onboardingStep)
    }
}

#Preview {
    OnboardingView()
}

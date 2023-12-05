//
//  InitialRegisterView.swift
//  iosApp
//
//  Created by Jan Janovec on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct InitialRegisterView: View {
    @Binding var authPage: AuthPage
    @StateObject private var viewModel: RegisterVM = .init()
    var body: some View {
        ZStack {
            switch viewModel.state.page {
            case .identification:
                RegistrationIdentificationStep()
            case .personalDetails:
                RegistrationPersonalStep()
            case .uploadPhoto:
                RegistrationUploadPhotoStep()
            case .confirmation:
                EmptyView()
            default:
                EmptyView()
            }
            VStack {
                Spacer()
                RoundedButton(text: "Continue", foreground: Color.white, background: LinearGradient(colors: [.red, .orange], startPoint: .bottomLeading, endPoint: .topTrailing)) {
                    withAnimation {
                        viewModel.nextStep()
                    }
                }
                .disabled(!viewModel.currentPageIsValid)
            }
        }
        .environmentObject(viewModel)
        .alert(viewModel.state.alert.title, isPresented: $viewModel.state.alert.isPresented, actions: {
            ForEach(viewModel.state.alert.actions, id: \.self) { action in
                Text("Action")
            }
        }, message: {
            Text(viewModel.state.alert.message)
        })
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .register)
}
